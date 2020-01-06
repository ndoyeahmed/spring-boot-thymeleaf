package com.penda.transfertargent.transfert.config;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.penda.transfertargent.transfert.dao.IUtilisateur;
import com.penda.transfertargent.transfert.model.Utilisateur;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@SuppressWarnings("unused")
public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private IUtilisateur iUtilisateur;

    protected Log logger = LogFactory.getLog(this.getClass());

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private AuthenticationSuccessHandler target = new SavedRequestAwareAuthenticationSuccessHandler();

    @Autowired
    public void setiUtilisateur(IUtilisateur iUtilisateur) {
        this.iUtilisateur = iUtilisateur;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        if (!hasTemporaryPassword(authentication)) {
            response.sendRedirect("/change-password");
        } else {
            handle(request, response, authentication);
            clearAuthenticationAttributes(request);
        }
    }

    private boolean hasTemporaryPassword(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Utilisateur utilisateur = iUtilisateur.findByLogin(user.getUsername());
        return utilisateur.isActive();
    }

    public void proceed(HttpServletRequest request,
                        HttpServletResponse response, Authentication auth) throws IOException, ServletException {
        handle(request, response, auth);
        clearAuthenticationAttributes(request);
    }

    protected void handle(HttpServletRequest request,
                          HttpServletResponse response, Authentication authentication)
            throws IOException {

        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            logger.debug(
                    "Response has already been committed. Unable to redirect to "
                            + targetUrl);
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {
        boolean isSuperAdmin = false;
        boolean isCaisse = false;
        boolean isAdmin = false;
        boolean isUser = false;
        Collection<? extends GrantedAuthority> authorities
                = authentication.getAuthorities();
        String role = "";
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_SUPERADMIN")) {
                role = grantedAuthority.getAuthority();
                isSuperAdmin = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_CAISSIER")) {
                role = grantedAuthority.getAuthority();
                isCaisse = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                role = grantedAuthority.getAuthority();
                isAdmin = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                role = grantedAuthority.getAuthority();
                isUser = true;
                break;
            }

            System.out.println("role : " + role);
        }

        if (isAdmin) {
            return "api/transaction/";
        } else if (isSuperAdmin) {
            return "api/partenaire/";
        } else if (isCaisse) {
            return "transfert/versement/";
        } else if (isUser) {
            return "api/transaction/";
        } else {
            throw new IllegalStateException();
        }
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

}

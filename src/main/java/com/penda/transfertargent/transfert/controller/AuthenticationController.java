package com.penda.transfertargent.transfert.controller;

import com.penda.transfertargent.transfert.config.MySimpleUrlAuthenticationSuccessHandler;
import com.penda.transfertargent.transfert.dao.IUtilisateur;
import com.penda.transfertargent.transfert.model.Utilisateur;
import com.penda.transfertargent.transfert.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/change-password")
public class AuthenticationController {
    @Autowired
    private IUtilisateur iUtilisateur;

    @Autowired
    private UtilisateurService utilisateurService;

    private MySimpleUrlAuthenticationSuccessHandler handler = new MySimpleUrlAuthenticationSuccessHandler();

    @GetMapping
    public String index() {
        return "change-password";
    }

    @PostMapping
    public void changePassword(HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam(name = "newPassword") String newPassword) throws IOException, ServletException {

        try {
            // handle password change
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();
            utilisateurService.changeUserPassword(user.getUsername(), newPassword);
            // proceed to the secured page
            handler.proceed(request, response, authentication);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/login");
        }
    }
}

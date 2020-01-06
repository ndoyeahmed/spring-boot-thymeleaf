package com.penda.transfertargent.transfert.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.penda.transfertargent.transfert.dao.IUtilisateur;
import com.penda.transfertargent.transfert.model.Role;
import com.penda.transfertargent.transfert.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
//ou @Component
public class CustumUserDetailsService implements UserDetailsService {
    @Autowired
    private IUtilisateur userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur user = userDAO.findByLogin(username);
        if (user != null) {
            return new User((user).getLogin(), user.getPwd(),
                    true, true, true, true, getAuthorities(user.getRoles()));
        }

        return null;
    }

    private Collection getAuthorities(List roles) {
        List authorities = new ArrayList();
        for (Object role : roles) {
            Role l = (Role) role;
            authorities.add(new SimpleGrantedAuthority(l.getLibRole()));
        }
        return authorities;
    }

}

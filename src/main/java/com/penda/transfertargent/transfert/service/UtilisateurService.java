package com.penda.transfertargent.transfert.service;

import com.penda.transfertargent.transfert.dao.IUtilisateur;
import com.penda.transfertargent.transfert.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService  {

    @Autowired
    private IUtilisateur iUtilisateur;

    @Autowired
    private BCryptPasswordEncoder encode;

    public boolean changeUserPassword(String username, String newPassword) throws Exception {
        try {
            Utilisateur utilisateur = iUtilisateur.findByLogin(username);
            if (utilisateur != null) {
                utilisateur.setPwd(encode.encode(newPassword));
                utilisateur.setActive(true);
                iUtilisateur.save(utilisateur);
                return true;
            } else throw new Exception("Username not found");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}

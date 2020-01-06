package com.penda.transfertargent.transfert.dao;

import com.penda.transfertargent.transfert.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUtilisateur extends JpaRepository<Utilisateur, Integer> {

    public Utilisateur findByLogin(String login);
    public Utilisateur findByEmail(String email);

}

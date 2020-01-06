package com.penda.transfertargent.transfert.dao;

import com.penda.transfertargent.transfert.model.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICompte extends JpaRepository<Compte, Integer> {
    @Query("SELECT MAX (c.id) FROM Compte c")
    public Integer getMaxId();

}

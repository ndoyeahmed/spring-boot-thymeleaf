package com.penda.transfertargent.transfert.dao;

import com.penda.transfertargent.transfert.model.Partenaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPartenaire extends JpaRepository<Partenaire, Integer> {

    public Partenaire findByNinea(String ninea);
    public Partenaire findByMail(String mail);

}

package com.penda.transfertargent.transfert.service;

import com.penda.transfertargent.transfert.dao.IClient;
import com.penda.transfertargent.transfert.dao.IPartenaire;
import com.penda.transfertargent.transfert.model.Partenaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartenaireClientService {
    @Autowired
    private IClient iclient;
    @Autowired
    private IPartenaire iPartenaire;

    public void savePartenaire(Partenaire partenaire) throws Exception {


        try {
            iPartenaire.save(partenaire);

        } catch (Exception e) {
            throw e;
        }

    }
    public List<Partenaire> getAll(){

        return (List<Partenaire>) iPartenaire.findAll();
    }

    public Optional<Partenaire> getOne(Integer id){

        return iPartenaire.findById(id);
    }

    public void addNew(Partenaire partenaire){
        iPartenaire.save(partenaire);
    }

    public void update(Partenaire partenaire){

        iPartenaire.save(partenaire);
    }



}

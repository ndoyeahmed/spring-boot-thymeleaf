package com.penda.transfertargent.transfert.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Partenaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String adresse;
    private String mail;
    private String tel;
    private String ninea;
    @OneToMany(mappedBy = "partenaire", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Compte>comptes;
    @OneToMany(mappedBy = "partenaire", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Utilisateur>utilisateurList;

    public Partenaire() {
    }

    public Partenaire(String nom, String adresse, String mail, String tel, String ninea, List<Compte> comptes) {
        this.nom = nom;
        this.adresse = adresse;
        this.mail = mail;
        this.tel = tel;
        this.ninea = ninea;
        this.comptes = comptes;
    }

    public List<Utilisateur> getUtilisateurList() {
        return utilisateurList;
    }

    public void setUtilisateurList(List<Utilisateur> utilisateurList) {
        this.utilisateurList = utilisateurList;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getMail() {
        return mail;
    }

    public String getTel() {
        return tel;
    }

    public String getNinea() {
        return ninea;
    }

    public List<Compte> getComptes() {
        return comptes;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setNinea(String ninea) {
        this.ninea = ninea;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }
}

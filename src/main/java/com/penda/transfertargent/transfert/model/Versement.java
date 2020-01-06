package com.penda.transfertargent.transfert.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Versement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;
    private float montant;
    @ManyToOne
    @JoinColumn(name = "idCompte")
    private Compte compte;
    @ManyToOne
    @JoinColumn(name = "idCreateur")
    private Utilisateur utilisateur;


    public Versement() {
    }

    public Versement(Date date, float montant, Compte compte) {
        this.date = date;
        this.montant = montant;
        this.compte = compte;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public float getMontant() {
        return montant;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }
}



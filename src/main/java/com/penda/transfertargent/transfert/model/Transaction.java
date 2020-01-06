package com.penda.transfertargent.transfert.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;
    private float montant;
    private String type;
    private String code;

    private float comm;
    private float commExp;
    private float commDest;
    private float commSys;
    private float taxe;

    @ManyToOne
    @JoinColumn(name = "idUserExp")
    private Utilisateur expediteur ;
    @ManyToOne
    @JoinColumn(name = "idUserDest")
    private Utilisateur destinataire ;

    @ManyToOne
    @JoinColumn(name ="idExped" )
    private Client clientExpediteur;
    @ManyToOne
    @JoinColumn(name ="idDest",insertable = false)
    private Client clientDestinataire;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }



    public float getComm() {
        return comm;
    }

    public void setComm(float comm) {
        this.comm = comm;
    }

    public float getCommExp() {
        return commExp;
    }

    public void setCommExp(float commExp) {
        this.commExp = commExp;
    }

    public float getCommDest() {
        return commDest;
    }

    public void setCommDest(float commDest) {
        this.commDest = commDest;
    }

    public float getCommSys() {
        return commSys;
    }

    public void setCommSys(float commSys) {
        this.commSys = commSys;
    }

    public float getTaxe() {
        return taxe;
    }

    public void setTaxe(float taxe) {
        this.taxe = taxe;
    }

    public Utilisateur getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(Utilisateur expediteur) {
        this.expediteur = expediteur;
    }

    public Utilisateur getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(Utilisateur destinataire) {
        this.destinataire = destinataire;
    }

    public Client getClientExpediteur() {
        return clientExpediteur;
    }

    public void setClientExpediteur(Client clientExpediteur) {
        this.clientExpediteur = clientExpediteur;
    }

    public Client getClientDestinataire() {
        return clientDestinataire;
    }

    public void setClientDestinataire(Client clientDestinataire) {
        this.clientDestinataire = clientDestinataire;
    }
}

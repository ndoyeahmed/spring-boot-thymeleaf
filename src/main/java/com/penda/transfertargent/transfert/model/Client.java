package com.penda.transfertargent.transfert.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String numPiece;
    private String nom;
    private String tel;

    @OneToMany(mappedBy = "clientExpediteur")
    private List<Transaction> transactionListExp;
    @OneToMany(mappedBy = "clientDestinataire")
    private List<Transaction> transactionListDest;

    public Client() {
    }

    public Client(String numPiece, String nom, String tel) {
        this.numPiece = numPiece;
        this.nom = nom;
        this.tel = tel;
    }

    public List<Transaction> getTransactionListExp() {
        return transactionListExp;
    }

    public void setTransactionListExp(List<Transaction> transactionListExp) {
        this.transactionListExp = transactionListExp;
    }

    public List<Transaction> getTransactionListDest() {
        return transactionListDest;
    }

    public void setTransactionListDest(List<Transaction> transactionListDest) {
        this.transactionListDest = transactionListDest;
    }

    public int getId() {
        return id;
    }

    public String getNumPiece() {
        return numPiece;
    }

    public String getNom() {
        return nom;
    }

    public String getTel() {
        return tel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumPiece(String numPiece) {
        this.numPiece = numPiece;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}



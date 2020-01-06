

package com.penda.transfertargent.transfert.model;


import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String pwd;
    private String nomComplet;
    private String email;
    private String photo;
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "idPartenaire")
    private Partenaire partenaire;
    @ManyToOne
    @JoinColumn(name = "idCompte")
    private Compte compte;

    @OneToMany(mappedBy = "expediteur")
    private List<Transaction> transactionListExp;
    @OneToMany(mappedBy = "destinataire")
    private List<Transaction> transactionListDest ;

    @OneToMany(mappedBy = "utilisateur")
    private List<Versement> versementList ;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "utilisateur_role",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
    @Transient
    private MultipartFile[] files;


    public Utilisateur() {
    }

    public Utilisateur(String login, String pwd, String nomComplet, String email, String photo, Partenaire partenaire, Compte compte, List<Transaction> transactionListExp, List<Transaction> transactionListDest) {
        this.login = login;
        this.pwd = pwd;
        this.nomComplet = nomComplet;
        this.email = email;
        this.photo = photo;
        this.partenaire = partenaire;
        this.compte = compte;
        this.transactionListExp = transactionListExp;
        this.transactionListDest = transactionListDest;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    public List<Versement> getVersementList() {
        return versementList;
    }

    public void setVersementList(List<Versement> versementList) {
        this.versementList = versementList;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Partenaire getPartenaire() {
        return partenaire;
    }

    public void setPartenaire(Partenaire partenaire) {
        this.partenaire = partenaire;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

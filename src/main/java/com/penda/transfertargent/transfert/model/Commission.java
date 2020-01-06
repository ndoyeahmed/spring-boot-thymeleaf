
package com.penda.transfertargent.transfert.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Commission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float montantMin;
    private float montantMax;
    private float comm;


    public Commission() {
    }

    public Commission(float montantMin, float montantMax, float comm) {
        this.montantMin = montantMin;
        this.montantMax = montantMax;
        this.comm = comm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMontantMin() {
        return montantMin;
    }

    public void setMontantMin(float montantMin) {
        this.montantMin = montantMin;
    }

    public float getMontantMax() {
        return montantMax;
    }

    public void setMontantMax(float montantMax) {
        this.montantMax = montantMax;
    }

    public float getComm() {
        return comm;
    }

    public void setComm(float comm) {
        this.comm = comm;
    }
}


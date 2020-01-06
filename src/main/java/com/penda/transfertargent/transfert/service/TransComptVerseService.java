package com.penda.transfertargent.transfert.service;

import com.penda.transfertargent.transfert.dao.ICompte;
import com.penda.transfertargent.transfert.dao.IPartenaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class TransComptVerseService {
    @Autowired
    private ICompte iCompte;
    public String genereCode(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        Integer i= iCompte.getMaxId();

       if (i==null){
           return "C-"+ Timestamp.from(Instant.now()) + "00001";

       }else {
           return "C-"+ Timestamp.from(Instant.now()) + new DecimalFormat("00000").format(i+1) ;
       }

    }
    @Autowired
    private IPartenaire iPartenaire;

}

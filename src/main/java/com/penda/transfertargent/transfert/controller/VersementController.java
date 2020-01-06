package com.penda.transfertargent.transfert.controller;

import com.penda.transfertargent.transfert.model.Versement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;

@Controller
//@RequestMapping(value = "/caisse")
@PreAuthorize("hasRole('CAISSIER')")
public class VersementController {

    @PreAuthorize("hasRole('CAISSIER')")
    @GetMapping("/transfert/versement/")
    public String index(Model model){
        Versement  versement = new Versement();
        versement.setDate(Calendar.getInstance().getTime());

        model.addAttribute("unVersement",versement);

        return "addVersement";
    }
    @PostMapping("/transfert/versement/add")
    public String index(@ModelAttribute("unVersement") Versement v, Model model){
        model.addAttribute("msg","post active");

        return "addVersement";
    }

}

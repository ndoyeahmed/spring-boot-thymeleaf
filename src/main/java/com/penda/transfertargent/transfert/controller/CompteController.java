package com.penda.transfertargent.transfert.controller;


import com.penda.transfertargent.transfert.model.Compte;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/compte")
public class CompteController {

    @GetMapping("/")
    public String index(Model model) {
        Compte compte = new Compte();
        model.addAttribute("unCompte", compte);

        return "addCompte";

    }

    @PostMapping("/add")
    public String index(@ModelAttribute("unCompte") Compte cpt, Model model) {

        model.addAttribute("msg", "post active");

        return "addCompte";
    }
}

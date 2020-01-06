package com.penda.transfertargent.transfert.controller;

import com.penda.transfertargent.transfert.model.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/utilisateur")
public class UtilisateurController {

    @GetMapping("/")
    public String index(Model model){
        Utilisateur utilisateur = new Utilisateur();
        model.addAttribute("unUtilisateur",utilisateur);

        return "addUser";
    }
    @PostMapping("/add")
    public String index(@ModelAttribute("unUtilisateur") Utilisateur u, Model model){
        model.addAttribute("msg","post active");

        return "addUser";
    }
}

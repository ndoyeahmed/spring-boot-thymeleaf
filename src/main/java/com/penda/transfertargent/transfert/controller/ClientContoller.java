package com.penda.transfertargent.transfert.controller;

import com.penda.transfertargent.transfert.model.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/client")
public class ClientContoller {

    @GetMapping("/")
    public String index(Model model) {
        Client client = new Client();
        model.addAttribute("unClient", client);

        return "addClient";

    }

    @PostMapping("/add")
    public String index(@ModelAttribute("unClient") Client c, Model model) {

        model.addAttribute("msg", "post active");

        return "addClient";
    }
}

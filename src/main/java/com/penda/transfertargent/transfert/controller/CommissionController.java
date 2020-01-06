package com.penda.transfertargent.transfert.controller;

import com.penda.transfertargent.transfert.model.Commission;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/commission")
public class CommissionController {

    @GetMapping("/")
    public String index(Model model) {
        Commission commission = new Commission();
        model.addAttribute("uneCommission", commission);

        return "addCommission";

    }

    @PostMapping("/add")
    public String index(@ModelAttribute("uneCommission") Commission com, Model model) {

        model.addAttribute("msg", "post active");

        return "addCommission";
    }
}

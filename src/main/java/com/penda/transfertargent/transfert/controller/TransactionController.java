package com.penda.transfertargent.transfert.controller;

import com.penda.transfertargent.transfert.model.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;

@Controller
@RequestMapping(value = "/api/transaction")
public class TransactionController {

        @GetMapping("/")
        public String index(Model model){
        Transaction transaction = new Transaction();
        transaction.setDate(Calendar.getInstance().getTime());

        model.addAttribute("uneTransaction",transaction);

            return "addTransaction";
        }
        @PostMapping("/add")
        public String index(@ModelAttribute("uneTransaction") Transaction p, Model model){
            model.addAttribute("msg","post active");

            return "addTransaction";
        }

    }

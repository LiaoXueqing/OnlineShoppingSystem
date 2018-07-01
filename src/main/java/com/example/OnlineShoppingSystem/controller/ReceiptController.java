package com.example.OnlineShoppingSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("invoice")
public class InvoiceController {
    @GetMapping()
    public String getInvoice(){
        return "invoice";
    }
}

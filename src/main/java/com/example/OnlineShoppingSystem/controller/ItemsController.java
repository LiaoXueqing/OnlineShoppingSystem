package com.example.OnlineShoppingSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("items")
public class ItemsController {
    @GetMapping()
    public String getItems(){
        return "items";
    }
}

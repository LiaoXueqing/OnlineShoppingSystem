package com.example.OnlineShoppingSystem.controller;

import com.example.OnlineShoppingSystem.entity.Item;
import com.example.OnlineShoppingSystem.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("items")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;
    @GetMapping()
    public String getItems(Model model){
        List<Item> itemList = itemRepository.findAll();
        model.addAttribute("itemList", itemList);
        return "items";
    }
}

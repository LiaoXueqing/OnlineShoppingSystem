package com.example.OnlineShoppingSystem.controller;

import com.example.OnlineShoppingSystem.entity.Item;
import com.example.OnlineShoppingSystem.repository.ItemRepository;
import com.example.OnlineShoppingSystem.service.ItemService;
import com.example.OnlineShoppingSystem.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RestController
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("index")
    public String toIndex(){
        return "index";
    }
    /**
     * 返回所有商品列表
     * @param model
     * @return
     */
    @GetMapping("/")
    public String getItems(Model model){
        List<Item> itemList = itemService.getAllItems();
        model.addAttribute("itemList", itemList);
        return "items";
    }

    /**
     * 按指定格式打印发票
     * @param cartItems
     * @return
     */
//    @PostMapping("/receipts")
    @RequestMapping(value = "/receipts", method = RequestMethod.POST)
    public Map<String, Object> getReceipt(@RequestBody String[] cartItems,Model model) {
        System.out.println("——————打印收据——————");
        Map<String, Object> map = new HashMap<>();
        double subTotal = itemService.getTotalMoneyAfterDiscount(cartItems);
        double discount = itemService.getTotalMoney(cartItems) - itemService.getTotalMoneyAfterDiscount(cartItems);
        map.put("items", itemService.getReceiptItem(cartItems));
        map.put("total", subTotal+"（元）");
        map.put("discount", discount+"（元）");
        System.out.println("map:"+map+"——————");
        model.addAttribute("map", map);
        return map;
    }
    @GetMapping("/myCart")
    public String getShoppings(){
        return "/mycart";
    }
}

package com.example.OnlineShoppingSystem.controller;

import com.example.OnlineShoppingSystem.entity.Item;
import com.example.OnlineShoppingSystem.repository.ItemRepository;
import com.example.OnlineShoppingSystem.service.ItemService;
import com.example.OnlineShoppingSystem.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @GetMapping("items")
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
    @PostMapping("receipt")
    public String getReceipt(@RequestBody String[] cartItems,Model model) {
//    public Map<String, Object> getReceipt(@RequestBody String[] cartItems,Model model) {
//    public Map<String, Object> getReceipt() {
        System.out.println("——————打印收据——————");
        Map<String, Object> result = new HashMap<>();
        double subTotal = itemService.getTotalMoneyAfterDiscount(cartItems);
        result.put("items", itemService.getReceiptItem(cartItems));
        result.put("total", subTotal);
        result.put("dec", itemService.getTotalMoney(cartItems) - subTotal);
        System.out.println("result:"+result+"——————");
        model.addAttribute("result", result);
        return "receipt";
//        return result;
    }
    @GetMapping("myCart")
    public String getShoppings(){
        return "/mycart";
    }
}

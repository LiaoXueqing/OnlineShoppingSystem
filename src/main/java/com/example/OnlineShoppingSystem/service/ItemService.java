package com.example.OnlineShoppingSystem.service;

import com.example.OnlineShoppingSystem.entity.Item;

import java.util.List;
import java.util.Map;

public interface ItemService {
    List<Item> getAllItems();
    double getTotalMoney(String[] items);
    double getTotalMoneyAfterDiscount(String[] items);
    List<Map<String, String>> getReceiptItem(String[] items);
}

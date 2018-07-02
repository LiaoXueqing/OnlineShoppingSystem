package com.example.OnlineShoppingSystem.service.impl;

import com.example.OnlineShoppingSystem.entity.Item;
import com.example.OnlineShoppingSystem.repository.ItemRepository;
import com.example.OnlineShoppingSystem.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class ItemServiceImpl  implements ItemService{
    @Autowired
    private ItemRepository itemRepository;
    private Item getItem(String item_str){
        Item item;
        int index = item_str.indexOf('-');
        if (index > 0) {
            String itemBarcode = item_str.substring(0, index);
            item = itemRepository.findByBarcode(itemBarcode);
        } else {
            item = itemRepository.findByBarcode(item_str);
        }
        return item;
    }
    private Integer getNumberOfItem(String item_str){
        Integer number = 1;
        int index = item_str.indexOf('-');
        if (index > 0) {
            number = Integer.parseInt(item_str.substring(index + 1));
        }
        return number;
    }

    /**
     * 同类优惠商品合并
     * @param cartItems
     * @return
     */
    private Map<String, Integer> getMerge(String[] cartItems) {
        Map<String, Integer> map = new HashMap<>();
        Item item;
        Integer number;
        for (String item_str : cartItems) {
            item = getItem(item_str);
            number = getNumberOfItem(item_str);
            if (map.containsKey(item.getBarcode())) {
                map.put(item.getBarcode(), map.get(item.getBarcode()) + number);
            }
            else {
                map.put(item.getBarcode(), number);
            }
        }
        return map;
    }

    /**
     * 计算每种商品价格——优惠后
     * @param item
     * @param number
     * @return
     */
    private double getSubTotal(Item item, int number) {
        double subTotal = 0;
        if ("BUY_TWO_GET_ONE_FREE".equals(item.getDiscount())) {
            int discount = number / 3;
            subTotal = item.getPrice() * (number - discount);
        } else {
            subTotal = item.getPrice() * number;
        }
        return subTotal;
    }
    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    /**
     * 计算购物车总价
     * @param cartItems
     * @return
     */
    public double getTotalMoney(String[] cartItems){
        Item item;
        double total = 0;
        for (String item_str : cartItems) {
            item = getItem(item_str);
            Integer number = getNumberOfItem(item_str);
            total += item.getPrice() * number;
        }
        return total;
    }

    /**
     * 计算优惠后总价
     * @param cartItems
     * @return
     */
    public double getTotalMoneyAfterDiscount(String[] cartItems){
        double total = 0;
        //<商品条码，商品数量>
        Map<String, Integer> map = getMerge(cartItems);
        for (String barcode : map.keySet()) {
            Item item = itemRepository.findByBarcode(barcode);
            total += getSubTotal(item, map.get(barcode));
        }
        return total;
    }

    /**
     * 按指定格式打印商品信息
     * @param cartItems
     * @return
     */
    public List<Map<String, String>> getReceiptItem(String[] cartItems){
        List<Map<String, String>> result = new ArrayList<>();
        Map<String, Integer> map = getMerge(cartItems);
        for (String barcode : map.keySet()) {
            Item item = itemRepository.findByBarcode(barcode);
            Map<String, String> cart_item = new LinkedHashMap<>();
            cart_item.put("name", item.getName());
            cart_item.put("number", map.get(barcode) + item.getUnits());
            cart_item.put("price", item.getPrice() + "（元）");
            cart_item.put("subTotal", getSubTotal(item, map.get(barcode)) + "（元）");
//            cart_item.put("weigh", item.getWeigh() + "");
            result.add(cart_item);
        }
        return result;
    }
}

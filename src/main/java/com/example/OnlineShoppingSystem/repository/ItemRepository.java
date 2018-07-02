package com.example.OnlineShoppingSystem.repository;

import com.example.OnlineShoppingSystem.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
    Item findByBarcode(String barcode);
}
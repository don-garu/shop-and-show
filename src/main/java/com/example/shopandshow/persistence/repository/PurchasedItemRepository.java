package com.example.shopandshow.persistence.repository;

import com.example.shopandshow.persistence.model.PurchasedItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasedItemRepository extends JpaRepository<PurchasedItem, Integer> {

    List<PurchasedItem> findAllByUserId(Integer userId);
}

package com.example.shopandshow.persistence.repository;

import com.example.shopandshow.persistence.model.PurchasedItem;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasedItemRepository extends JpaRepository<PurchasedItem, Integer> {

    Collection<PurchasedItem> findAllByUserId(Integer userId);
}

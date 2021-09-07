package com.example.shopandshow.persistence.repository;

import com.example.shopandshow.persistence.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}

package com.example.shopandshow.persistence.repository;

import com.example.shopandshow.persistence.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantRepository extends JpaRepository<Merchant, Integer> {

    Optional<Merchant> findByUserId(Integer user_id);
}

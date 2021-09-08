package com.example.shopandshow.persistence.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchasedItem {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer itemId;

    private String shippingAddress;

    private LocalDate purchaseDate;

    @ManyToOne
    private User user;

    @Builder
    public PurchasedItem(Integer itemId, String shippingAddress, LocalDate purchaseDate,
        User user) {
        this.itemId = itemId;
        this.shippingAddress = shippingAddress;
        this.purchaseDate = purchaseDate;
        this.user = user;
    }
}

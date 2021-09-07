package com.example.shopandshow.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer price;
    private Integer remainingQuantity;

    private String name;

    @Builder
    public Item(Integer price, Integer remainingQuantity, String name, Merchant merchant) {
        this.price = price;
        this.remainingQuantity = remainingQuantity;
        this.name = name;
        this.bannerImagePath = bannerImagePath;
        this.descriptionImagePath = descriptionImagePath;
        this.merchant = merchant;
    }

    private String bannerImagePath;
    private String descriptionImagePath;

    @ManyToOne
    private Merchant merchant;

    public void editRemainingQuantity(Integer remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public void editPrice(Integer price) {
        this.price = price;
    }

    public void editBannerImagePath(String imagePath) {
        this.bannerImagePath = imagePath;
    }

    public void editDescriptionImagePath(String imagePath) {
        this.descriptionImagePath = imagePath;
    }
}

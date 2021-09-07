package com.example.shopandshow.persistence.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Merchant {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer wallet;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itemList;

    @Builder
    public Merchant(Integer wallet, User user) {
        this.wallet = wallet;
        this.user = user;
    }

    public void deposit(Integer amount) {
        wallet += amount;
    }

    public void addItem(Item item) {
        itemList.add(item);
    }
}

package com.example.shopandshow.persistence.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Builder
    public Merchant(Integer wallet, User user) {
        this.wallet = wallet;
        this.user = user;
    }
}

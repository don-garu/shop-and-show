package com.example.shopandshow.persistence.model;

import com.example.shopandshow.persistence.dto.UserDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer age;

    private Gender gender;

    private String name;
    private String password;
    private String address;

    @OneToOne
    private Merchant merchant;

    @Builder
    public User(Integer age, Gender gender, String name, String password, String address) {
        this.age = age;
        this.gender = gender;
        this.name = name;
        this.password = password;
        this.address = address;
    }

    public void editMerchant(Merchant merchant) {
        this.merchant = merchant;
    }
}

package com.example.shopandshow.persistence.dto;

import com.example.shopandshow.persistence.model.Gender;
import lombok.Builder;
import lombok.Value;

public interface UserDTO {

    @Value
    @Builder
    class Create {

        Integer age;
        String name;
        String password;
        String address;
        Gender gender;
    }

    @Value
    @Builder
    class Result {

        Integer id;
        Integer age;
        String name;
        String address;
        Gender gender;
    }
}

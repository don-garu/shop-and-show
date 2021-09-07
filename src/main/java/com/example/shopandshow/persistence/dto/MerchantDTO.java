package com.example.shopandshow.persistence.dto;

import lombok.Builder;
import lombok.Value;

public interface MerchantDTO {

    @Value
    @Builder
    class Create {

        Integer userId;
        Integer wallet;
    }

    @Value
    @Builder
    class Result {

        Integer id;
        Integer userId;
        Integer wallet;
    }

    @Value
    @Builder
    class Update {

        Integer id;
        Integer amount;
    }
}

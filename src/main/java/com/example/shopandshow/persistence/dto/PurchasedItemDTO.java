package com.example.shopandshow.persistence.dto;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Value;

public interface PurchasedItemDTO {

    @Value
    @Builder
    class Result {

        Integer itemId;
        String shippingAddress;
        LocalDate purchaseDate;
    }
}

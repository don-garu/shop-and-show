package com.example.shopandshow.persistence.dto;

import com.sun.istack.NotNull;
import io.micrometer.core.lang.Nullable;
import lombok.Builder;
import lombok.Value;

public interface ItemDTO {

    @Value
    @Builder
    class Create {

        Integer merchantId;
        Integer price;
        Integer quantity;

        String name;
    }

    @Value
    @Builder
    class Update {

        @NotNull
        Integer id;

        @Nullable
        Integer price;

        @Nullable
        Integer remainingQuantity;

        @Nullable
        String bannerImagePath;

        @Nullable
        String descriptionImagePath;
    }

    @Value
    @Builder
    class Result {

        Integer id;
        Integer price;
        Integer remainingQuantity;

        String name;
        String bannerImagePath;
        String descriptionImagePath;
    }
}

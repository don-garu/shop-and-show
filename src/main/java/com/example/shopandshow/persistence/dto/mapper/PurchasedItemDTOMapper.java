package com.example.shopandshow.persistence.dto.mapper;

import com.example.shopandshow.persistence.dto.PurchasedItemDTO;
import com.example.shopandshow.persistence.model.PurchasedItem;
import org.springframework.stereotype.Component;

@Component
public class PurchasedItemDTOMapper {

    public PurchasedItemDTO.Result toDTO(PurchasedItem entity) {
        return PurchasedItemDTO.Result.builder()
            .itemId(entity.getItemId())
            .shippingAddress(entity.getShippingAddress())
            .purchaseDate(entity.getPurchaseDate())
            .build();
    }
}

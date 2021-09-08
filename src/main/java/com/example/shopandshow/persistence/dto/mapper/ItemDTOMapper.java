package com.example.shopandshow.persistence.dto.mapper;

import com.example.shopandshow.persistence.dto.ItemDTO;
import com.example.shopandshow.persistence.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemDTOMapper {

    public ItemDTO.Result toDTO(Item item) {
        return ItemDTO.Result.builder()
            .id(item.getId())
            .name(item.getName())
            .price(item.getPrice())
            .remainingQuantity(item.getRemainingQuantity())
            .bannerImagePath(item.getBannerImagePath())
            .descriptionImagePath(item.getDescriptionImagePath())
            .build();
    }
}

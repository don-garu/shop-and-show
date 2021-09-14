package com.example.shopandshow.persistence.dto.mapper;

import com.example.shopandshow.persistence.dto.ItemDTO;
import com.example.shopandshow.persistence.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemDTOMapper {

    public ItemDTO.Result toDTO(Item entity) {
        return ItemDTO.Result.builder()
            .id(entity.getId())
            .name(entity.getName())
            .price(entity.getPrice())
            .remainingQuantity(entity.getRemainingQuantity())
            .bannerImagePath(entity.getBannerImagePath())
            .descriptionImagePath(entity.getDescriptionImagePath())
            .build();
    }
}

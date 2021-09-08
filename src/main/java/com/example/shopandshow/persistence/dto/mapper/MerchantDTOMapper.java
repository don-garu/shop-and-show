package com.example.shopandshow.persistence.dto.mapper;

import com.example.shopandshow.persistence.dto.MerchantDTO;
import com.example.shopandshow.persistence.model.Merchant;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MerchantDTOMapper {

    private final ItemDTOMapper itemDTOMapper;

    public MerchantDTO.Result toDTO(Merchant entity) {
        return baseBuilder(entity)
            .build();
    }

    public MerchantDTO.Result toDTOWithItems(Merchant entity) {
        return baseBuilder(entity)
            .items(entity.getItems()
                .stream().map(itemDTOMapper::toDTO).collect(Collectors.toList()))
            .build();
    }

    private MerchantDTO.Result.ResultBuilder baseBuilder(Merchant entity) {
        return MerchantDTO.Result.builder()
            .id(entity.getId())
            .userId(entity.getUser().getId())
            .wallet(entity.getWallet());
    }
}

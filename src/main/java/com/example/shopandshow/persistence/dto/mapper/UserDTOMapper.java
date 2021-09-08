package com.example.shopandshow.persistence.dto.mapper;

import com.example.shopandshow.persistence.dto.UserDTO;
import com.example.shopandshow.persistence.model.User;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserDTOMapper {

    private final PurchasedItemDTOMapper purchasedItemDTOMapper;

    public UserDTO.Result toDTO(User entity) {
        return baseBuilder(entity)
            .build();
    }

    public UserDTO.Result toDTOWithPurchasedItems(User entity) {
        return baseBuilder(entity)
            .purchasedItems(entity.getPurchasedItems()
                .stream().map(purchasedItemDTOMapper::toDTO).collect(Collectors.toList()))
            .build();
    }

    private UserDTO.Result.ResultBuilder baseBuilder(User entity) {
        return UserDTO.Result.builder()
            .id(entity.getId())
            .age(entity.getAge())
            .name(entity.getName())
            .address(entity.getAddress())
            .gender(entity.getGender());
    }
}

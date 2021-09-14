package com.example.shopandshow.persistence.dto.mapper;

import com.example.shopandshow.persistence.dto.PostDTO;
import com.example.shopandshow.persistence.model.Post;
import org.springframework.stereotype.Component;

@Component
public class PostDTOMapper {

    public PostDTO.Result toDTO(Post entity) {
        return PostDTO.Result.builder()
            .id(entity.getId())
            .userId(entity.getUserId())
            .imagePath(entity.getImagePath())
            .body(entity.getBody())
            .build();
    }
}

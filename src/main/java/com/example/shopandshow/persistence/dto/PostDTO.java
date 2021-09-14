package com.example.shopandshow.persistence.dto;

import com.sun.istack.NotNull;
import org.springframework.lang.Nullable;
import lombok.Builder;
import lombok.Value;

public interface PostDTO {

    @Value
    @Builder
    class Create {

        Integer userId;

        String imagePath;
        String body;
    }

    @Value
    @Builder
    class Update {

        @NotNull
        Integer id;

        @Nullable
        String imagePath;
        @Nullable
        String body;
    }

    @Value
    @Builder
    class Result {

        Integer id;
        Integer userId;

        String imagePath;
        String body;
    }
}

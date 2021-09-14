package com.example.shopandshow.persistence.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "post")
public class Post {

    @Transient
    public static final String SEQUENCE_NAME = "post_sequence";

    @Id
    private Integer id;

    private Integer userId;

    private String imagePath;
    private String body;

    @Builder
    public Post(Integer userId, String imagePath, String body) {
        this.userId = userId;
        this.imagePath = imagePath;
        this.body = body;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void editImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void editBody(String body) {
        this.body = body;
    }
}

package com.example.shopandshow.persistence.model;

import javax.persistence.GeneratedValue;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
public class Post {

    @Id
    @GeneratedValue
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

    public void editImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void editBody(String body) {
        this.body = body;
    }
}

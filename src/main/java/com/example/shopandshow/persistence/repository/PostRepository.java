package com.example.shopandshow.persistence.repository;

import com.example.shopandshow.persistence.model.Post;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, Integer> {

    List<Post> findAllByUserId(Integer userId);
}

package com.example.shopandshow.controller;

import com.example.shopandshow.persistence.dto.PostDTO;
import com.example.shopandshow.service.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDTO.Result create(@RequestBody PostDTO.Create createDto) {
        return postService.create(createDto);
    }

    @GetMapping("/{id}")
    public PostDTO.Result read(@PathVariable Integer id) {
        return postService.read(id);
    }

    @GetMapping("/user/{userId}")
    public List<PostDTO.Result> readAll(@PathVariable Integer userId) {
        return postService.readAll(userId);
    }

    @PutMapping("/update")
    public PostDTO.Result update(@RequestBody PostDTO.Update updateDto) {
        return postService.update(updateDto);
    }
}

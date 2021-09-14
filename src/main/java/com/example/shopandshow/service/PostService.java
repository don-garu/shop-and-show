package com.example.shopandshow.service;

import com.example.shopandshow.persistence.dto.PostDTO;
import com.example.shopandshow.persistence.dto.mapper.PostDTOMapper;
import com.example.shopandshow.persistence.model.Post;
import com.example.shopandshow.persistence.model.User;
import com.example.shopandshow.persistence.repository.PostRepository;
import com.example.shopandshow.persistence.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PostDTOMapper postDTOMapper;

    @Transactional
    public PostDTO.Result create(PostDTO.Create dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND,
                "User " + dto.getUserId() + " Not Exists"));

        Post post = Post.builder()
            .userId(user.getId())
            .imagePath(dto.getImagePath())
            .body(dto.getBody())
            .build();
        postRepository.save(post);

        return postDTOMapper.toDTO(post);
    }

    @Transactional
    public PostDTO.Result update(PostDTO.Update dto) {
        Post post = postRepository.findById(dto.getId()).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Post " + dto.getId() + " Not Exists"));

        if (dto.getImagePath() != null) {
            post.editImagePath(dto.getImagePath());
        }
        if (dto.getBody() != null) {
            post.editBody(dto.getBody());
        }

        return postDTOMapper.toDTO(post);
    }

    @Transactional(readOnly = true)
    public PostDTO.Result read(Integer id) {
        Post found = postRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post " + id + "Not Exists"));

        return postDTOMapper.toDTO(found);
    }

    @Transactional(readOnly = true)
    public List<PostDTO.Result> readAll(Integer userId) {
        List<Post> posts = postRepository.findAllByUserId(userId);

        return posts.stream().map(postDTOMapper::toDTO).collect(Collectors.toList());
    }
}

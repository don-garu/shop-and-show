package com.example.shopandshow.service;

import com.example.shopandshow.persistence.dto.UserDTO;
import com.example.shopandshow.persistence.model.User;
import com.example.shopandshow.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserDTO.Result create(UserDTO.Create dto) {
        User user = User.builder()
            .age(dto.getAge())
            .name(dto.getName())
            .password(dto.getPassword())
            .address(dto.getAddress())
            .gender(dto.getGender())
            .build();
        userRepository.save(user);

        return UserDTO.Result.builder()
            .id(user.getId())
            .age(user.getAge())
            .name(user.getName())
            .address(user.getAddress())
            .gender(user.getGender())
            .build();
    }

    @Transactional(readOnly = true)
    public UserDTO.Result login(String name, String password) {
        User found = userRepository.findByName(name).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "User [" + name + "] has not found"));

        if (!found.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password Not match!");
        }

        return UserDTO.Result.builder()
            .id(found.getId())
            .age(found.getAge())
            .name(found.getName())
            .address(found.getAddress())
            .gender(found.getGender())
            .build();
    }
}

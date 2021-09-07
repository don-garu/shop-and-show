package com.example.shopandshow.controller;

import com.example.shopandshow.persistence.dto.ItemDTO;
import com.example.shopandshow.service.ItemService;
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
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDTO.Result create(@RequestBody ItemDTO.Create createDto) {
        return itemService.create(createDto);
    }

    @GetMapping("/{id}")
    public ItemDTO.Result read(@PathVariable Integer id) {
        return itemService.read(id);
    }

    @PutMapping("/update")
    public ItemDTO.Result update(@RequestBody ItemDTO.Update updateDto) {
        return itemService.update(updateDto);
    }
}

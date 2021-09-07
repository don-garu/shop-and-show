package com.example.shopandshow.controller;

import com.example.shopandshow.persistence.dto.MerchantDTO;
import com.example.shopandshow.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/merchant")
@RestController
public class MerchantController {

    private final MerchantService merchantService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public MerchantDTO.Result create(@RequestBody MerchantDTO.Create createDto) {
        return merchantService.create(createDto);
    }

    @GetMapping("/login")
    public MerchantDTO.Result login(@RequestParam Integer userId) {
        return merchantService.login(userId);
    }

    @PutMapping("/deposit")
    public MerchantDTO.Result deposit(@RequestBody MerchantDTO.Update updateDto) {
        return merchantService.deposit(updateDto);
    }
}

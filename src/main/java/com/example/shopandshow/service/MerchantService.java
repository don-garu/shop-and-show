package com.example.shopandshow.service;

import com.example.shopandshow.persistence.dto.MerchantDTO;
import com.example.shopandshow.persistence.model.Merchant;
import com.example.shopandshow.persistence.model.User;
import com.example.shopandshow.persistence.repository.MerchantRepository;
import com.example.shopandshow.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class MerchantService {

    private final MerchantRepository merchantRepository;
    private final UserRepository userRepository;

    @Transactional
    public MerchantDTO.Result create(MerchantDTO.Create dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Exists"));
        Merchant merchant = Merchant.builder()
            .user(user)
            .wallet(dto.getWallet())
            .build();

        user.editMerchant(merchant);

        merchantRepository.save(merchant);

        return MerchantDTO.Result.builder()
            .id(merchant.getId())
            .userId(merchant.getUser().getId())
            .wallet(merchant.getWallet())
            .build();
    }

    @Transactional(readOnly = true)
    public MerchantDTO.Result login(Integer userId) {
        Merchant found = merchantRepository.findByUserId(userId).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Merchant with User " + userId + " Not Exists"));

        return MerchantDTO.Result.builder()
            .id(found.getId())
            .userId(found.getUser().getId())
            .wallet(found.getWallet())
            .build();
    }

    @Transactional
    public MerchantDTO.Result deposit(MerchantDTO.Update dto) {
        Merchant merchant = merchantRepository.findById(dto.getId()).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Merchant Not Exists"));

        merchant.deposit(dto.getAmount());

        return MerchantDTO.Result.builder()
            .id(merchant.getId())
            .userId(merchant.getUser().getId())
            .wallet(merchant.getWallet())
            .build();
    }
}

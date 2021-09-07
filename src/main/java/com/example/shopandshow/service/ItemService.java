package com.example.shopandshow.service;

import com.example.shopandshow.persistence.dto.ItemDTO;
import com.example.shopandshow.persistence.model.Item;
import com.example.shopandshow.persistence.model.Merchant;
import com.example.shopandshow.persistence.repository.ItemRepository;
import com.example.shopandshow.persistence.repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final MerchantRepository merchantRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public ItemDTO.Result create(ItemDTO.Create dto) {
        Merchant merchant = merchantRepository.findById(dto.getMerchantId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Merchant " + dto.getMerchantId() + " Not Exists"));
        Item item = Item.builder()
            .name(dto.getName())
            .price(dto.getPrice())
            .remainingQuantity(dto.getQuantity())
            .merchant(merchant)
            .build();

        merchant.addItem(item);
        itemRepository.save(item);

        return ItemDTO.Result.builder()
            .id(item.getId())
            .price(item.getPrice())
            .name(item.getName())
            .remainingQuantity(item.getRemainingQuantity())
            .bannerImagePath(item.getBannerImagePath())
            .descriptionImagePath(item.getDescriptionImagePath())
            .build();
    }

    @Transactional
    public ItemDTO.Result update(ItemDTO.Update dto) {
        Item item = itemRepository.findById(dto.getId()).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Item " + dto.getId() + " Not Exists"));

        if (dto.getPrice() != null) {
            item.editPrice(dto.getPrice());
        }
        if (dto.getRemainingQuantity() != null) {
            item.editRemainingQuantity(dto.getRemainingQuantity());
        }
        if (dto.getBannerImagePath() != null) {
            item.editBannerImagePath(dto.getBannerImagePath());
        }
        if (dto.getDescriptionImagePath() != null) {
            item.editDescriptionImagePath(dto.getDescriptionImagePath());
        }

        return ItemDTO.Result.builder()
            .id(item.getId())
            .price(item.getPrice())
            .name(item.getName())
            .remainingQuantity(item.getRemainingQuantity())
            .bannerImagePath(item.getBannerImagePath())
            .descriptionImagePath(item.getDescriptionImagePath())
            .build();
    }

    @Transactional(readOnly = true)
    public ItemDTO.Result read(Integer id) {
        Item found = itemRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Item " + id + " Not Exists"));

        return ItemDTO.Result.builder()
            .id(found.getId())
            .price(found.getPrice())
            .name(found.getName())
            .remainingQuantity(found.getRemainingQuantity())
            .bannerImagePath(found.getBannerImagePath())
            .descriptionImagePath(found.getDescriptionImagePath())
            .build();
    }
}

package com.mackmarton.household.services;

import com.mackmarton.household.dto.ItemDTO;
import com.mackmarton.household.entities.Item;
import com.mackmarton.household.mappers.ItemMapper;
import com.mackmarton.household.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;

    public ItemDTO createItem(ItemDTO item) {
        return itemMapper.toDto(itemRepository.save(itemMapper.toEntity(item)));
    }

    public Item getOrCreateItem(ItemDTO itemDTO) {
        if (itemDTO.getId() != null) {
            var item = itemRepository.findById(itemDTO.getId());
            if(item.isPresent()){
                return item.get();
            }
        }
        return itemRepository.save(itemMapper.toEntity(itemDTO));
    }
}

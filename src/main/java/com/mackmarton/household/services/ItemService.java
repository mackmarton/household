package com.mackmarton.household.services;

import com.mackmarton.household.dto.ItemDTO;
import com.mackmarton.household.entities.Item;
import com.mackmarton.household.mappers.ItemMapper;
import com.mackmarton.household.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;
    private final RecipeIngredientService recipeIngredientService;

    public ItemDTO createItem(ItemDTO item) {
        return itemMapper.toDto(itemRepository.save(itemMapper.toEntity(item)));
    }

    public Item getOrCreateItem(ItemDTO itemDTO) {
        if (itemDTO.getId() != null) {
            var item = itemRepository.findById(itemDTO.getId());
            if (item.isPresent()) {
                return item.get();
            }
        }
        return itemRepository.save(itemMapper.toEntity(itemDTO));
    }

    public List<ItemDTO> getAllItems() {
        return itemMapper.toDtos(itemRepository.findAll());
    }

    public Optional<ItemDTO> getItemById(int id) {
        return itemRepository.findById(id).map(itemMapper::toDto);
    }

    public Optional<ItemDTO> updateItem(int id, ItemDTO itemDTO) {
        if (!itemDTO.getId().equals(id)) {
            return Optional.empty();
        }

        return itemRepository.findById(id).map(item -> {
            item.setName(itemDTO.getName());
            item.setIsOnShoppingList(itemDTO.getIsOnShoppingList());
            return itemMapper.toDto(itemRepository.save(item));
        });
    }

    public boolean deleteItem(int id) {
        recipeIngredientService.deleteByIngredientId(id);
        return itemRepository.findById(id).map(item -> {
            itemRepository.delete(item);
            return true;
        }).orElse(false);
    }
}

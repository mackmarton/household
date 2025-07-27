package com.mackmarton.household.services;

import com.mackmarton.household.dto.DishwashingDTO;
import com.mackmarton.household.entities.Dishwashing;
import com.mackmarton.household.mappers.DishwashingMapper;
import com.mackmarton.household.repositories.DishwashingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DishwashingService {
    public static final String MARCI_NAME = "Marci";
    public static final String REKA_NAME = "Reka";
    private final DishwashingRepository dishwashingRepository;
    private final DishwashingMapper dishwashingMapper;

    public List<DishwashingDTO> getAllDishwashingEvents() {
        return dishwashingMapper.toDtos(dishwashingRepository.findAll());
    }

    public DishwashingDTO addDishwashingForMarci() {
        Dishwashing dishwashing = new Dishwashing();
        dishwashing.setName(MARCI_NAME);
        dishwashing.setTime(LocalDateTime.now());
        return dishwashingMapper.toDto(dishwashingRepository.save(dishwashing));
    }

    public DishwashingDTO addDishwashingForReka() {
        Dishwashing dishwashing = new Dishwashing();
        dishwashing.setName(REKA_NAME);
        dishwashing.setTime(LocalDateTime.now());
        return dishwashingMapper.toDto(dishwashingRepository.save(dishwashing));
    }

    public String getWhoseTurnItIs() {
        int marciWashedDishes = dishwashingRepository.countDishwashingByNameEquals(MARCI_NAME);
        int rekaWashedDishes = dishwashingRepository.countDishwashingByNameEquals(REKA_NAME);
        Dishwashing lastDishwashing = dishwashingRepository.findFirstByOrderByTimeDesc();

        if (rekaWashedDishes > marciWashedDishes) {
            return MARCI_NAME;
        } else if (marciWashedDishes > rekaWashedDishes) {
            return REKA_NAME;
        } else if (lastDishwashing != null && MARCI_NAME.equals(lastDishwashing.getName())) {
            return REKA_NAME;
        } else {
            return MARCI_NAME;
        }
    }
}

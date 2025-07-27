package com.mackmarton.household.controllers;

import com.mackmarton.household.dto.DishwashingDTO;
import com.mackmarton.household.services.DishwashingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dishwashing")
public class DishwashingController {

    private final DishwashingService dishwashingService;

    @GetMapping
    public ResponseEntity<List<DishwashingDTO>> getAllDishwashingEvents() {
        return ResponseEntity.ok(dishwashingService.getAllDishwashingEvents());
    }

    @PostMapping("/marci")
    public ResponseEntity<DishwashingDTO> addDishwashingForMarci() {
        return ResponseEntity.ok(dishwashingService.addDishwashingForMarci());
    }

    @PostMapping("/reka")
    public ResponseEntity<DishwashingDTO> addDishwashingForReka() {
        return ResponseEntity.ok(dishwashingService.addDishwashingForReka());
    }

    @GetMapping("/turn")
    public ResponseEntity<String> getWhoseTurnItIs() {
        return ResponseEntity.ok(dishwashingService.getWhoseTurnItIs());
    }
}

package com.mackmarton.household.controllers;

import com.mackmarton.household.dto.DishwashingDTO;
import com.mackmarton.household.services.DishwashingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Dishwashing", description = "Dishwashing management API")
public class DishwashingController {

    private final DishwashingService dishwashingService;

    @GetMapping
    @Operation(summary = "Get all dishwashing events", description = "Retrieves the history of all dishwashing events")
    @ApiResponse(responseCode = "200", description = "Dishwashing events retrieved successfully")
    public ResponseEntity<List<DishwashingDTO>> getAllDishwashingEvents() {
        return ResponseEntity.ok(dishwashingService.getAllDishwashingEvents());
    }

    @PostMapping("/marci")
    @Operation(summary = "Add dishwashing event for Marci", description = "Records a new dishwashing event for Marci")
    @ApiResponse(responseCode = "200", description = "Dishwashing event recorded successfully")
    public ResponseEntity<DishwashingDTO> addDishwashingForMarci() {
        return ResponseEntity.ok(dishwashingService.addDishwashingForMarci());
    }

    @PostMapping("/reka")
    @Operation(summary = "Add dishwashing event for Reka", description = "Records a new dishwashing event for Reka")
    @ApiResponse(responseCode = "200", description = "Dishwashing event recorded successfully")
    public ResponseEntity<DishwashingDTO> addDishwashingForReka() {
        return ResponseEntity.ok(dishwashingService.addDishwashingForReka());
    }

    @GetMapping("/turn")
    @Operation(summary = "Get whose turn it is", description = "Determines whose turn it is to wash dishes based on history")
    @ApiResponse(responseCode = "200", description = "Successfully determined whose turn it is")
    public ResponseEntity<String> getWhoseTurnItIs() {
        return ResponseEntity.ok(dishwashingService.getWhoseTurnItIs());
    }
}
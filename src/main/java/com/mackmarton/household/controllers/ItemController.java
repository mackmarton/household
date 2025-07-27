package com.mackmarton.household.controllers;

import com.mackmarton.household.dto.ItemDTO;
import com.mackmarton.household.services.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
@Tag(name = "Item", description = "Item management API")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    @Operation(summary = "Get all items", description = "Retrieves all items from the database")
    @ApiResponse(responseCode = "200", description = "Items retrieved successfully")
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get item by ID", description = "Retrieves a specific item by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item found"),
            @ApiResponse(responseCode = "404", description = "Item not found", content = @Content)
    })
    public ResponseEntity<ItemDTO> getItemById(
            @Parameter(description = "Item ID") @PathVariable int id) {
        return itemService.getItemById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create item", description = "Creates a new item with provided details")
    @ApiResponse(responseCode = "200", description = "Item created successfully")
    public ResponseEntity<ItemDTO> createItem(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Item details")
            @RequestBody ItemDTO item) {
        return ResponseEntity.ok(itemService.createItem(item));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update item", description = "Updates an existing item with new details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request - ID mismatch", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item not found", content = @Content)
    })
    public ResponseEntity<ItemDTO> updateItem(
            @Parameter(description = "Item ID") @PathVariable int id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Updated item details")
            @RequestBody ItemDTO item) {
        var updatedItem = itemService.updateItem(id, item);
        if (updatedItem.isEmpty()) {
            if (!item.getId().equals(id)) {
                return ResponseEntity.badRequest().build();
            }
        }

        return updatedItem
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete item", description = "Deletes an item by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Item not found", content = @Content)
    })
    public ResponseEntity<Void> deleteItem(
            @Parameter(description = "Item ID") @PathVariable int id) {
        boolean deleted = itemService.deleteItem(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
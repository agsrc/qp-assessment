package edu.akshay.grocerybooking.controller;


import edu.akshay.grocerybooking.dto.GroceryItemDTO;
import edu.akshay.grocerybooking.entity.GroceryItem;
import edu.akshay.grocerybooking.service.GroceryItemService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/admin", headers = "X-API-Version=v1")
public class AdminController {

    private final GroceryItemService groceryItemService;

    public AdminController(GroceryItemService groceryItemService) {
        this.groceryItemService = groceryItemService;
    }


    @PostMapping("/groceryItems")
    public ResponseEntity<?> addGroceryItem(@RequestBody GroceryItemDTO groceryItemDTO) {
        try {
            GroceryItem savedItem = groceryItemService.addGroceryItem(groceryItemDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add grocery item: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add grocery item: " + e.getMessage());
        }
    }

    @GetMapping("/groceryItems")
    public ResponseEntity<List<GroceryItem>> getAllGroceryItems() {
        List<GroceryItem> groceryItems = groceryItemService.getAllGroceryItems();
        return ResponseEntity.ok(groceryItems);
    }

    @GetMapping("/groceryItems/{id}")
    public ResponseEntity<GroceryItem> getGroceryItemById(@PathVariable Long id) {
        try {
            GroceryItem groceryItem = groceryItemService.getGroceryItemById(id);
            return ResponseEntity.ok(groceryItem);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/groceryItems/{id}")
    public ResponseEntity<?> updateGroceryItem(@PathVariable Long id, @RequestBody GroceryItemDTO updatedGroceryItemDTO) {
        try {
            GroceryItem updatedItem = groceryItemService.updateGroceryItem(id, updatedGroceryItemDTO);
            return ResponseEntity.ok(updatedItem);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update grocery item: " + e.getMessage());
        }
    }

    @DeleteMapping("/groceryItems/{id}")
    public ResponseEntity<?> removeGroceryItem(@PathVariable Long id) {
        try {
            groceryItemService.removeGroceryItem(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to remove grocery item: " + e.getMessage());
        }
    }

    @PutMapping("/groceryItems/{id}/inventory")
    public ResponseEntity<?> manageInventory(@PathVariable Long id, @RequestParam int quantity) {
        try {
            GroceryItem updatedItem = groceryItemService.manageInventory(id, quantity);
            return ResponseEntity.ok(updatedItem);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to manage inventory: " + e.getMessage());
        }
    }
}




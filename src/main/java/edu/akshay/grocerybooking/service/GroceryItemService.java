package edu.akshay.grocerybooking.service;

import edu.akshay.grocerybooking.dto.GroceryItemDTO;
import edu.akshay.grocerybooking.entity.GroceryItem;
import edu.akshay.grocerybooking.repository.GroceryItemRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GroceryItemService {

    private final GroceryItemRepository groceryItemRepository;

    public GroceryItemService(GroceryItemRepository groceryItemRepository) {
        this.groceryItemRepository = groceryItemRepository;
    }

    public GroceryItem addGroceryItem(GroceryItemDTO groceryItemDTO) {
        if (groceryItemRepository.existsByName(groceryItemDTO.getName())) {
            throw new DataIntegrityViolationException("Grocery Item with name '" + groceryItemDTO.getName() + "' already exists");
        }
        GroceryItem groceryItem = convertToEntity(groceryItemDTO);
        return groceryItemRepository.save(groceryItem);
    }

    public List<GroceryItem> getAllGroceryItems() {
        return groceryItemRepository.findAll();
    }

    public GroceryItem getGroceryItemById(Long id) {
        return groceryItemRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Grocery item not found with id: " + id));
    }

    public GroceryItem updateGroceryItem(Long id, GroceryItemDTO updatedGroceryItemDTO) {
        GroceryItem existingItem = getGroceryItemById(id);
        existingItem.setName(updatedGroceryItemDTO.getName());
        existingItem.setPrice(updatedGroceryItemDTO.getPrice());
        existingItem.setQuantity(updatedGroceryItemDTO.getQuantity());
        return groceryItemRepository.save(existingItem);
    }

    public void removeGroceryItem(Long id) {
        if (!groceryItemRepository.existsById(id)) {
            throw new NoSuchElementException("Grocery item not found with id: " + id);
        }
        groceryItemRepository.deleteById(id);
    }

    public GroceryItem manageInventory(Long id, int quantity) {
        GroceryItem existingItem = getGroceryItemById(id);
        existingItem.setQuantity(existingItem.getQuantity() + quantity);
        return groceryItemRepository.save(existingItem);
    }

    private GroceryItem convertToEntity(GroceryItemDTO groceryItemDTO) {
        GroceryItem groceryItem = new GroceryItem();
        groceryItem.setName(groceryItemDTO.getName());
        groceryItem.setPrice(groceryItemDTO.getPrice());
        groceryItem.setQuantity(groceryItemDTO.getQuantity());
        return groceryItem;
    }
}


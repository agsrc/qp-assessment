package edu.akshay.grocerybooking.controller;

import edu.akshay.grocerybooking.entity.GroceryItem;
import edu.akshay.grocerybooking.entity.Order;
import edu.akshay.grocerybooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/groceryItems", headers = "X-API-Version=v1")
    public ResponseEntity<List<GroceryItem>> getAllGroceryItems() {
        List<GroceryItem> groceryItems = userService.getAllGroceryItems();
        return ResponseEntity.ok(groceryItems);
    }

    @PostMapping(value = "/orders", headers = "X-API-Version=v1")
    public ResponseEntity<Order> createOrder(@RequestBody List<Long> itemIds) {
        Order order = userService.createOrder(itemIds);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}


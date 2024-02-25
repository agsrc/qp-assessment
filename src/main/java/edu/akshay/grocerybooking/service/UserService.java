package edu.akshay.grocerybooking.service;

import edu.akshay.grocerybooking.entity.GroceryItem;
import edu.akshay.grocerybooking.entity.Order;
import edu.akshay.grocerybooking.repository.GroceryItemRepository;
import edu.akshay.grocerybooking.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    public List<GroceryItem> getAllGroceryItems() {
        return groceryItemRepository.findAll();
    }

    public Order createOrder(List<Long> itemIds) {
        List<GroceryItem> items = groceryItemRepository.findAllById(itemIds);
        Order order = new Order();
        order.setItems(items);
        return orderRepository.save(order);
    }
}


package edu.akshay.grocerybooking.repository;

import edu.akshay.grocerybooking.entity.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {

    boolean existsByName(String name);
}

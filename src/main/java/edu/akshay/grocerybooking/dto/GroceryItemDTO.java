package edu.akshay.grocerybooking.dto;

public record GroceryItemDTO(String name, double price, int quantity) {

    public String getName() {
        return name;
    }


    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}


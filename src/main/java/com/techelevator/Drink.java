package com.techelevator;

public class Drink extends Item {

    public Drink(String slot, String name, double price) {
        super(slot, price, name);
    }


    @Override
    public String makeSound() {
        return "Glug Glug, Yum!";
    }
}
package com.techelevator;

public class Chips extends Item {

    public Chips(String slot, String name, double price) {
        super(slot, price, name);
    }


    @Override
    public String makeSound() {
        return "Crunch Crunch, Yum!";
    }
}
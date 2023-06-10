package com.techelevator;

public class Gum extends Item {

    public Gum(String slot, String name, double price) {
        super(slot, price, name);
    }


    @Override
    public String makeSound() {
        return "Chew Chew, Yum!";
    }
}
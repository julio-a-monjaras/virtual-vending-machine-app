package com.techelevator;

public class Candy extends Item {

    public Candy(String slot, String name, double price) {
        super(slot, price, name);
    }


    @Override
    public String makeSound() {
        return "Munch Munch, Yum!";
    }
}
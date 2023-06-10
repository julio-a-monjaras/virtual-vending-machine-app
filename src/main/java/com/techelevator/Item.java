package com.techelevator;

public abstract class Item {

    private final String slotID;
    private final double price;
    private final String name;
    private int capacity;

    public Item(String slotID, double price, String name) {
        this.slotID = slotID;
        this.name = name;
        this.price = price;
        capacity = 5;
    }

    //Method that reduces capacity after purchase
    public void purchase() {
        this.capacity--;
    }

    public abstract String makeSound();

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }


    //Formatting item info to be displayed
public void itemToString() {
        System.out.printf("%-3s %-3s %-18s %-3s %s %s", slotID, " | ", name, " |", "$", String.format("%.2f", price));
        System.out.println();
    }
}
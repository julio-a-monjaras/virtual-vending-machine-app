package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {

    //Path for the file containing the items
    private static final String FILE_PATH = "vendingmachine.csv";

    private Map<String, Item> fullInventory = new HashMap<>();

    private static final Map<String, Integer> sales = new HashMap<>();


    //Populates the map according to slot ID and Item info
    public void buildInventory() throws FileNotFoundException {
        File vmFile = new File(FILE_PATH);

        try (Scanner scan = new Scanner(vmFile)) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] productValues = line.split("\\|");
                String slot = productValues[0];
                String name = productValues[1];
                double price = Double.parseDouble(productValues[2]);
                String type = productValues[3];

                //Assigns items info to different item objects
                if (type.equals("Chip")) {
                    fullInventory.put(slot, new Chips(slot, name, price));
                } else if (type.equals("Candy")) {
                    fullInventory.put(slot, new Candy(slot, name, price));
                } else if (type.equals("Drink")) {
                    fullInventory.put(slot, new Drink(slot, name, price));
                } else if (type.equals("Gum")) {
                    fullInventory.put(slot, new Gum(slot, name, price));
                }
                //Updates sales map to be used later
                sales.put(name, 0);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void increaseSales(String productName) {
        sales.put(productName, sales.get(productName) + 1);
    }

    //Formats sales report log
    public static void salesReportLog() {
        for (Map.Entry<String, Integer> item : sales.entrySet()) {
            SLogger.log(item.getKey() + " | " + item.getValue());
        }
        SLogger.log("\n**TOTAL SALES** $" + String.format("%.2f", Purchase.getTotalSales()) + "\n");
    }

    //Formats sales report to be displayed on the console
    public static void salesReport() {
        for (Map.Entry<String, Integer> item : sales.entrySet()) {
            System.out.println(String.format(item.getKey() + " | " + item.getValue()));
        }
        System.out.println("\n**TOTAL SALES** $" + String.format("%.2f", Purchase.getTotalSales()) + "\n");
    }

    public void setFullInventory(Map<String, Item> inventory) {
        fullInventory = inventory;
    }

    //Displays all items to the console
    public void displayInventory() {
        for (Map.Entry<String, Item> item : fullInventory.entrySet()) {
            item.getValue().itemToString();
        }
    }

    //Returns a map with the full inventory built from the file path
    public Map<String, Item> getFullInventory() {
        return fullInventory;
    }

    //Gets a single item according to it's slot for purchase
    public Item getProduct(String slot) {
        return fullInventory.get(slot);
    }
}
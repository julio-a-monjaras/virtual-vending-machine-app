package com.techelevator;

import com.techelevator.view.Menu;

import java.util.InputMismatchException;

import java.util.Scanner;

public class Purchase {
    // Vending Machine Purchase Menu Options.
    private final static String FEED_MONEY = "Feed money";
    private final static String SELECT_PRODUCT = "Select Product";
    private final static String FINISH_TRANSACTION = "Finish Transaction";
    private final static String[] MENU_OPTIONS = {FEED_MONEY, SELECT_PRODUCT, FINISH_TRANSACTION};

    private final Inventory inventory;
    private final Menu menu;

    private double moneyProvided = 0.00;
    private final Scanner scannerIn = new Scanner(System.in);
    private static double totalSales = 0.00;

    public Purchase(Inventory inventory, Menu menu) {
        this.inventory = inventory;
        this.menu = menu;
    }

    //Main method of the purchase menu. It displays all the options available for option "Purchase" in the main menu,
    //calling its respectively method handling the logic of each option.
    public void displayPurchaseMenu() {
        while (true) {
            System.out.println("Current money provided: " + "$" + String.format("%.2f", moneyProvided));      //Fixed to show the amount of money with two decimals.
            String choice = (String) menu.getChoiceFromOptions(MENU_OPTIONS);

            if (choice.equals(FEED_MONEY)) {
                setMoneyProvided();
            } else if (choice.equals(SELECT_PRODUCT)) {
                selectProduct();

            } else if (choice.equals(FINISH_TRANSACTION)) {
                finishTransaction();
            }
        }
    }

    // This method adds money to vending machine.
    public void setMoneyProvided() {
        System.out.println("Insert money");

        double moneyDouble = verifyMoneyInput();

        this.moneyProvided += moneyDouble;
        TLogger.log("FEED MONEY: $" + String.format("%.2f", moneyDouble) + " New Balance: $" + String.format("%.2f", moneyProvided));
        displayPurchaseMenu();
    }

    // This method allows user to select product from vending machine.

    public void selectProduct() {
        inventory.displayInventory();
        System.out.println("Select slot ID");
        String choice = scannerIn.nextLine();

        if(!inventory.getFullInventory().containsKey(choice)){
            System.out.println("Product code does not exist");
            displayPurchaseMenu();
        }
        if(inventory.getProduct(choice).getCapacity() == 0){
            System.out.println("Item is currently sold out");
            displayPurchaseMenu();
        }
        if(inventory.getProduct(choice).getPrice() >= moneyProvided){
            System.out.println("Not enough money for the requested item");
            displayPurchaseMenu();
        }

        updateProduct(choice);

        //Old code that handled complex logic//
//        if (inventory.getFullInventory().containsKey(choice)) {
//            if (inventory.getProduct(choice).getCapacity() != 0) {
//                if (inventory.getProduct(choice).getPrice() <= moneyProvided) {
//                    inventory.getProduct(choice).purchase();
//                    Inventory.increaseSales(inventory.getProduct(choice).getName());
//                    totalSales += inventory.getProduct(choice).getPrice();
//                    System.out.println(inventory.getProduct(choice).toString());
//                    System.out.println(inventory.getProduct(choice).makeSound());
//                    moneyProvided -= inventory.getProduct(choice).getPrice();
//                    TLogger.log("ITEM PURCHASED: " + inventory.getProduct(choice).toString() + " New Balance: $" + String.format("%.2f", moneyProvided));
//
//                } else {
//                    System.out.println("Not enough money for the requested item");
//                    displayPurchaseMenu();
//                }
//
//            } else {
//                System.out.println("Item is currently sold out");
//                displayPurchaseMenu();
//            }
//        } else {
//            System.out.println("Product code does not exist");
//            displayPurchaseMenu();
//        }
    }
    //Prints out change depending on money provided.
    //Formats and logs change given.
    //Resets money provided to 0 after transaction is complete
    //Runs the run method code in VendingMachineCLI
    public void finishTransaction() {
        System.out.println(Money.getChange(moneyProvided));
        TLogger.log("GIVE CHANGE: $" + String.format("%.2f", moneyProvided) + " New Balance: " + "$0.00");
        moneyProvided = 0;
        System.out.println("Transaction complete");
        VendingMachineCLI.run();
    }

    //Helper method for the setMoneyProvided() method
    //by verifying if the user is prompting the correct
    //input type, handles InputMismatchException as well as
    //if the user prompts a negative amount.
    //In a real world vending machine, there may not be reason to
    //code something like this, but probably for validating if the user
    //introduced real money would be.
    public double verifyMoneyInput(){
        double money = 0;
        try{
            money = scannerIn.nextDouble();
        } catch(InputMismatchException e){
            System.err.println("Please enter a valid amount!");
            scannerIn.nextLine();
            displayPurchaseMenu();
        }
        finally {
            scannerIn.nextLine();
        }
        if((money <= 0)){
            System.err.println("Please enter a valid amount!");
            displayPurchaseMenu();
        }
        return money;
    }
    // Updates the information of selected product in the inventory
    // Decreases capacity of selected product
    // Updates totalSales
    // Logs and formats item purchased
    public void updateProduct(String choice){
        Item currentItem = inventory.getProduct(choice);
        currentItem.purchase();
        Inventory.increaseSales(currentItem.getName());
        totalSales += currentItem.getPrice();
        currentItem.itemToString();
        System.out.println(currentItem.makeSound());
        moneyProvided -= currentItem.getPrice();
        TLogger.log("ITEM PURCHASED: " + currentItem.toString() + " New Balance: $" + String.format("%.2f", moneyProvided)); //Change the to string method
    }

    public static double getTotalSales() {
        return totalSales;
    }
}
package com.techelevator;

import com.techelevator.view.Menu;

import java.io.FileNotFoundException;
import java.util.Scanner;


public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String MAIN_MENU_OPTION_SALESREPORT = "Hidden Menu";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_SALESREPORT};

    private static Inventory inventory = new Inventory();

    private static Menu menu;

    public VendingMachineCLI(Menu menu) {
        this.menu = menu;
    }


    public static void run() {
        while (true) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                // display vending machine items
                inventory.displayInventory();

            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                // do purchase
                Purchase purchase = new Purchase(inventory, new Menu(System.in, System.out));
                purchase.displayPurchaseMenu();

            } else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
                System.out.println("Exiting the vending machine. Have a good day!\n");
                Inventory.salesReportLog();
                System.exit(0);
            } else if (choice.equals(MAIN_MENU_OPTION_SALESREPORT)) {
                String password = "12345";
                Scanner sc = new Scanner(System.in);
                System.out.println("Please enter the password for access this menu");
                String userInput = sc.nextLine();
                if (userInput.equals(password)) {
                    System.out.println("Sales report printed successfully\n");
                    Inventory.salesReport();
                } else {
                    System.out.println("Wrong password");
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            inventory.buildInventory();

        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }
}
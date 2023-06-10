package com.techelevator;

public class Money {

    //Separates dollars from cents in order to calculate the lowest amount of coins for the change

    public static String getChange(double amount) {
        int quarters;
        int dimes;
        int nickles;

        int cents = (int) (amount * 100);
        quarters = cents / 25;
        cents = cents % 25;
        dimes = cents / 10;
        cents = cents % 10;
        nickles = cents / 5;
        cents = cents % 5;
        String message = "";

        //Returns a message if there are existing coins to be returned
        if (quarters > 0) {
            message += "Quarters: " + quarters + "   ";
        }
        if (dimes > 0) {
            message += "Dimes: " + dimes + "   ";
        }
        if (nickles > 0) {
            message += "Nickles: " + nickles + "   ";
        }
        if (cents > 0) {
            message += "Cents: " + cents + "   ";
        }
        if (quarters == 0 && dimes == 0 && nickles == 0 && cents == 0) {
            message = "No change returned";
        }
        return message;
    }
}
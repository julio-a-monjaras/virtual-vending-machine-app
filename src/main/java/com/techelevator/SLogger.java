package com.techelevator;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SLogger {

    //We formatted the file name to contain the month, day, year, and time in hours, minutes, and seconds
    public static void log(String message) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy_HH-mm-ss");
        String local = LocalDateTime.now().format(dateTimeFormatter);
        String localDate = "SalesReport/" + local + ".txt";
        File folderSales = new File("SalesReport");

        File f = new File(localDate);

        //We create the SalesReport directory if it does not exist
        try {
            if (!folderSales.exists()) {
                folderSales.mkdir();
            }
        } catch (SLoggerException e) {
            throw new SLoggerException("Folder was not created.");
        }

        //We create the sales report txt file if it does not exist
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            new SLoggerException("Error. File already exists.");
        }
        try (PrintWriter dataOutput = new PrintWriter(new FileOutputStream(localDate, true))) {

            dataOutput.println(message);
        } catch (IOException e) {
            new SLoggerException("Error. Wasn't able to log the message.");
        }
    }
}
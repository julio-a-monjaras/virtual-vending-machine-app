package com.techelevator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

public class TLogger {

    //We formatted the file name to contain the date
    public static void log(String message) {
        String localDate = "Logs/" + LocalDate.now() + ".txt";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        String localDateTime = "[" + LocalDateTime.now().format(dateTimeFormatter).toString() + "]";
        File f = new File("Logs", localDate);
        File folderLogs = new File("Logs");

        //We create the SalesReport directory if it does not exist
        try {
            if (!folderLogs.exists()) {
                folderLogs.mkdir();
            }
        } catch (TLoggerException e) {
            new TLoggerException("Folder was not created.");
        }

        //We create the sales report txt file if it does not exist
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            new TLoggerException("Error. File already exists.");
        }

        try (PrintWriter dataOutput = new PrintWriter(new FileOutputStream(localDate, true))) {
            dataOutput.println(localDateTime + " " + message);
        } catch (IOException e) {
            new TLoggerException("Error. Wasn't able to log the message.");
        }
    }
}
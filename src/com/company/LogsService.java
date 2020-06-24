package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * Classname: LogsService
 *
 * @version 24.06.2020
 * @author Kopach Daria
 * Module 4 task 2
 *
 * Home Task: Multi-threading.
 *
 * 1. Use the file from the previous task - logs.txt.
 *
 * 2. Create a class that manages logs in this file.
 *
 * 3. Create a method that finds all the ERROR logs for a specific date and
 *  write them into a specific file (name = ERROR  + Date  + .log)
 *
 * 4. In your main class develop a functionality to create  5 such a files
 * for 5 different days. Launch them in consistent way (one after another).
 *
 * 5. Repeat the above  task in parallel way. Multi-threading.
 *
 * 6. Compare the results.
 *
 */

public class LogsService {

    private String dateTime;

    // generate empty constructor

    public LogsService() {
    }


    /** generate the full constructor
     *
     * @param dateTime String
     */

    public LogsService(String dateTime) {
        this.dateTime = dateTime;
    }

    // Getter for parameter dateTime
    public String getDateTime() {
        return dateTime;
    }

    // Setter for parameter dateTime
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Main method to execute
     * @param str String params.
     * @throws IOException throws error if there is no file exists.
     *
     */

    public void getLogsByDate(String str) throws IOException {

        // return time start
        LocalDateTime start = LocalDateTime.now();

        System.out.println(str + " Start time for search - " + start);

        List<String> errorLinesList = Files.lines(Paths.get("/Users/Nastya/Desktop/logs.txt"))
                .filter(line -> line.contains(str))
                .filter(line -> line.contains("ERROR"))
                .collect(Collectors.toList());

        int countLines = errorLinesList.size();

        LocalDateTime finish = LocalDateTime.now();

        long duration = ChronoUnit.MILLIS.between(start, finish);

        System.out.println("There are " + countLines + " ERROR lines." + " on " + str);
        System.out.println("Execution time: " + duration);

        String stringData = "";
        for (String line : errorLinesList) {
            stringData += line + "\n";
        }

        // path for new files

        String filePath = "/Users/Nastya/Desktop/ERROR-by-date- " + str + ".txt";

        // put the results into file

        Files.write(Paths.get(filePath), stringData.getBytes());

    }


}


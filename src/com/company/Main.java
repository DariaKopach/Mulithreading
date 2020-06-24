package com.company;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 *
 * Classname: Main
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

public class Main {

    /**
     *  Thread class to implement multithreading log search
     */

    static class MyThread extends Thread {

        private String date;

        MyThread(String date){
            this.date = date;
        }

        /**
         *  A method which initialize LogsManager class and calls its method "getErrorByDate"
         */
        @Override
        public void run() {
            LogsService service = new LogsService();
            try {
                service.getLogsByDate(date);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Main method to execute
     * @param args String params.
     * @throws IOException throws error if there is no file exists.
     */

    public static void main(String[] args) throws IOException {

        LogsService service1 = new LogsService();

        // start time for consistent way

        LocalDateTime startConsistent = LocalDateTime.now();

        // creating 5 such a files for 5 different days for consistent way

        new MyThread("2020-01-12").start();
        new MyThread("2020-01-13").start();
        new MyThread("2020-01-14").start();
        new MyThread("2019-01-15").start();
        new MyThread("2020-01-16").start();

        // finish time for consistent way

        LocalDateTime finishConsistent = LocalDateTime.now();

        // the different between star and finish

        long consistentTime = ChronoUnit.MILLIS.between(startConsistent, finishConsistent);

        System.out.println("Time for consistent way is: "
                + consistentTime + " milliseconds");

        // start time for parallel way

        LocalDateTime startParallel = LocalDateTime.now();

        // creating 5 such a files for 5 different days for parallel way

        service1.getLogsByDate("2020-01-12");
        service1.getLogsByDate("2020-01-13");
        service1.getLogsByDate("2020-01-14");
        service1.getLogsByDate("2020-01-15");
        service1.getLogsByDate("2020-01-16");

        // finish time for parallel way

        LocalDateTime finishParallel = LocalDateTime.now();

        long parallelTime = ChronoUnit.MILLIS.between(startParallel, finishParallel);

        // the different between star and finish

        System.out.println("Time for parallel way is: "
                + parallelTime + " milliseconds");

        // 6. Compare the results of two ways


        if (consistentTime > parallelTime) {
            System.out.println("\n Consequent time is less than Multi-Threading");
        } else if (consistentTime < parallelTime) {
            System.out.println("\n Multi-Threading time is less than Consequent");
        } else {
            System.out.println("\n Time for both methods are the same");
        }

    }
}

/*
Time for consistent way is: 402 milliseconds
2020-01-12 Start time for search - 2020-06-24T15:07:55.458
2020-01-12 Start time for search - 2020-06-24T15:07:55.510
2020-01-13 Start time for search - 2020-06-24T15:07:55.541
2020-01-14 Start time for search - 2020-06-24T15:07:55.554
2019-01-15 Start time for search - 2020-06-24T15:07:55.555
2020-01-16 Start time for search - 2020-06-24T15:07:55.556
There are 1 ERROR lines. on 2020-01-14
Execution time: 35943
There are 1 ERROR lines. on 2020-01-16
Execution time: 37138
There are 1 ERROR lines. on 2020-01-13
Execution time: 37653
There are 1 ERROR lines. on 2020-01-12
Execution time: 38544
There are 0 ERROR lines. on 2019-01-15
Execution time: 38466
2020-01-13 Start time for search - 2020-06-24T15:08:34.085
There are 1 ERROR lines. on 2020-01-12
Execution time: 38604
There are 1 ERROR lines. on 2020-01-13
Execution time: 12591
2020-01-14 Start time for search - 2020-06-24T15:08:46.679
There are 1 ERROR lines. on 2020-01-14
Execution time: 5937
2020-01-15 Start time for search - 2020-06-24T15:08:52.619
There are 0 ERROR lines. on 2020-01-15
Execution time: 6488
2020-01-16 Start time for search - 2020-06-24T15:08:59.174
There are 1 ERROR lines. on 2020-01-16
Execution time: 7386
Time for parallel way is:  milliseconds

Multi-Threading time is less than Consequent
71105
Process finished with exit code 0

 */
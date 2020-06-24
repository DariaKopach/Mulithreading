package com.company;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class Main {

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

    public static void main(String[] args) throws IOException {

        LogsService service1 = new LogsService();

        // @param startConsistent return time start consistent way
        LocalDateTime startConsistent = LocalDateTime.now();

        // creating 5 such a files for 5 different days for consistent way
        new MyThread("2019-10-13").start();
        new MyThread("2019-10-16").start();
        new MyThread("2019-10-17").start();
        new MyThread("2019-10-18").start();
        new MyThread("2020-02-04").start();

        // @param finishConsistent return time finish consistent way
        LocalDateTime finishConsistent = LocalDateTime.now();

        long consistentTime = ChronoUnit.MILLIS.between(startConsistent, finishConsistent);

        System.out.println("TOTAL DURATION of threads: "
                + consistentTime + " milliseconds");

        // @param startParallel return time start parallel way
        LocalDateTime startParallel = LocalDateTime.now();

        // creating 5 such a files for 5 different days for parallel way
        service1.getLogsByDate("2019-10-13");
        service1.getLogsByDate("2019-10-16");
        service1.getLogsByDate("2019-10-17");
        service1.getLogsByDate("2019-10-18");
        service1.getLogsByDate("2020-02-04");

        // @param finishParallel return time finish parallel way
        LocalDateTime finishParallel = LocalDateTime.now();

        long parallelTime = ChronoUnit.MILLIS.between(startParallel, finishParallel);

        System.out.println("TOTAL DURATION concequently: "
                + parallelTime + " milliseconds");

        /*
         * Comparing the duration results of two methods
         */
        if (consistentTime > parallelTime) {
            System.out.println("\n Consequent time is best than Multi-Threading");
        } else if (consistentTime < parallelTime) {
            System.out.println("\n Multi-Threading time is best than Consequent");
        } else {
            System.out.println("\n Time both methods is the same");
        }

    }
}
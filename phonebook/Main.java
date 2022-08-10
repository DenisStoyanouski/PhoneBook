package phonebook;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int count = 0;

    static long startTime;

    static long finishTime;

    static ArrayList<String> find = new ArrayList<>();

    static ArrayList<Member> directory = new ArrayList<>();

    public static void main(String[] args) {

        File smallFind = new File("d:\\JAVA\\Phone Book\\Phone Book\\task\\src\\phonebook\\small_find.txt");
        File smallDirectory = new File("d:\\JAVA\\Phone Book\\Phone Book\\task\\src\\phonebook\\small_directory.txt");
        // create list find

        try {
            Scanner scanner = new Scanner(smallFind);
            do {
                find.add(scanner.nextLine());
            } while(scanner.hasNextLine());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // create list directory
        try {
            Scanner scan = new Scanner(smallDirectory);
            do {
                Member m = new Member(scan.nextInt(), scan.nextLine());
                directory.add(m);
            } while(scan.hasNextLine());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    static private void startLinearSearch() {
        System.out.println("Start searching (linear search)...");
        //create start time in milliseconds
        startTime = System.currentTimeMillis();




        //create finish time in milliseconds;
        finishTime = System.currentTimeMillis();
        System.out.printf("Found %d/%d entries. Time take: %s %s %s", count, count,
                convertMsToMin(startTime,finishTime),convertMsToSec(startTime, finishTime),convertMsToMs(startTime, finishTime));
    }

    static private String convertMsToMin(long startTime, long finishTime) {
        long time = (finishTime - startTime) / 1000 / 60;
        return  time != 0 ? time + " min." : "";
    }

    static private String convertMsToSec(long startTime, long finishTime) {
        long time  = (finishTime - startTime) / 1000 % 60;
        return  time != 0 ? time + " sec." : "";
    }

    static private String convertMsToMs(long startTime, long finishTime) {
        long time = (finishTime - startTime) % 1000;
        return  time != 0 ? time + " ms." : "";
    }

}

     class Member implements Comparable<Member> {
        int phoneNumber;
        String name;


        public Member (int phoneNumber, String name) {
            this.phoneNumber = phoneNumber;
            this.name = name;

        }

        public String getName() {
            return this.name;
        }

        @Override
        public int compareTo(Member m) {
            return this.name.compareTo(m.getName());
        }
    }


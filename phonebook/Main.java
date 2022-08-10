package phonebook;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    static int count = 0;

    static long startTime;

    static long finishTime;

    static ArrayList<String> find = new ArrayList<>();

    static ArrayList<Member> directory = new ArrayList<>();

    public static void main(String[] args) {
        createFindList();
        createDirectory();
        startLinearSearch();
        startBJ();

    }

    static private void createFindList() {
        File smallFind = new File("d:\\JAVA\\Phone Book\\Phone Book\\task\\src\\phonebook\\find.txt");
        try {
            Scanner scanner = new Scanner(smallFind);
            do {
                find.add(scanner.nextLine());
            } while(scanner.hasNextLine());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static private void createDirectory() {
        File smallDirectory = new File("d:\\JAVA\\Phone Book\\Phone Book\\task\\src\\phonebook\\directory.txt");
        // create list directory
        try {
            Scanner scan = new Scanner(smallDirectory);
            do {
                Member m = new Member(scan.nextInt(), scan.nextLine().trim());
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

        for(String name : find) {
            for(Member member : directory) {
                if (name.equals(member.getName())) {
                    count++;
                }
            }
        }
        //create finish time in milliseconds;
        finishTime = System.currentTimeMillis();
        System.out.printf("Found %d/%d entries. Time take: %s %s %s%n", count, count,
                convertMsToMin(startTime,finishTime),convertMsToSec(startTime, finishTime),convertMsToMs(startTime, finishTime));
    }

    static private void startBJ() {
        System.out.println("Start searching (bubble sort + jump search)...");
        //create start time in milliseconds
        startTime = System.currentTimeMillis();
        long startSortingTime = System.currentTimeMillis();
        Collections.sort(find);
        Collections.sort(directory);
        long finishSortingTime = System.currentTimeMillis();
        count = 0;
        long startSearchingTime = System.currentTimeMillis();
        for(String name : find) {
            for(Member member : directory) {
                if (name.equals(member.getName())) {
                    count++;
                }
            }
        }
        long finishSearchingTime = System.currentTimeMillis();
        //create finish time in milliseconds;
        finishTime = System.currentTimeMillis();

        System.out.printf("Found %d/%d entries. Time take: %s %s %s%n", count, count,
                convertMsToMin(startTime,finishTime), convertMsToSec(startTime, finishTime),convertMsToMs(startTime, finishTime));

        System.out.printf("Sorting time: %s %s %s%n", convertMsToMin(startSortingTime, finishSortingTime),convertMsToSec(startSortingTime, finishSortingTime),convertMsToMs(startSortingTime, finishSortingTime));

        System.out.printf("Searching time: %s %s %s%n", convertMsToMin(startSearchingTime,finishSearchingTime),convertMsToSec(startSearchingTime, finishSearchingTime),convertMsToMs(startSearchingTime, finishSearchingTime));
    }

    static private String convertMsToMin(long startTime, long finishTime) {
        long time = (finishTime - startTime) / 1000 / 60;
        return  time + " min.";
    }

    static private String convertMsToSec(long startTime, long finishTime) {
        long time  = (finishTime - startTime) / 1000 % 60;
        return  time + " sec.";
    }

    static private String convertMsToMs(long startTime, long finishTime) {
        long time = (finishTime - startTime) % 1000;
        return  time + " ms.";
    }

}

     class Member implements Comparable<Member> {
        int phoneNumber;
        String name;


        public Member (int phoneNumber, String name) {
            this.phoneNumber = phoneNumber;
            this.name = name;

        }

        public int getPhoneNumber() {
            return this.phoneNumber;
        }

        public String getName() {
            return this.name;
        }

        @Override
        public int compareTo(Member m) {
            return this.name.compareTo(m.getName());
        }
    }


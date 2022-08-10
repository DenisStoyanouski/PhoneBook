package phonebook;

import java.io.*;
import java.util.Scanner;

public class Main {

    static int count = 0;

    static long startTime;

    static long finishTime;

    public static void main(String[] args) {
        System.out.println("Start searching...");
        //create start time in milliseconds
        startTime = System.currentTimeMillis();

        String name = "";
        File smallFind = new File("d:\\JAVA\\Phone Book\\Phone Book\\task\\src\\phonebook\\small_find.txt");
        File directory = new File("d:\\JAVA\\Phone Book\\Phone Book\\task\\src\\phonebook\\small_directory.txt");
        try {
            Scanner scanner = new Scanner(smallFind);
            do {
                name = scanner.nextLine();
                Scanner scan = new Scanner(directory);
                do {
                    String phone = scan.nextLine();
                    if (phone.contains(name)) {
                        count++;
                        break;
                    }
                } while(scan.hasNextLine());
            } while(scanner.hasNextLine());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

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
        String name;
        String phoneNumber;

        public Member (String name, String phoneNumber) {
            this.name = name;
            this.phoneNumber = phoneNumber;
        }

        public String getName() {
            return this.name;
        }

        @Override
        public int compareTo(Member m) {
            return this.name.compareTo(m.getName());
        }
    }


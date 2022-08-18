package phonebook;

import java.io.*;
import java.util.*;

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
        startQuickSortBinarySearch();
        doHashMap();

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

    //BubbleSort
    /*public class BubbleSort implements Sortable {

        @Override
        public boolean sort(List<Contact> people, long maxTime) {
            int length = people.size();
            maxTime += System.currentTimeMillis();
            for (int i = 0; i < length - 1; i++) {
                for (int j = 0; j < length - i - 1; j++) {
                    if (System.currentTimeMillis() > maxTime) {
                        return false;
                    }
                    if (people.get(j).getName().compareTo(people.get(j + 1).getName()) > 0) {
                        Contact temp = people.get(j);
                        people.set(j, people.get(j + 1));
                        people.set(j + 1, temp);
                    }
                }
            }
            return true;
        }
    }*/
    //JumpSearch
    /*public class JumpSearch implements Searchable {

        @Override
        public int search(List<Contact> arr, String name) {
            int curr = 0;
            int prev = 0;
            int last = arr.size() - 1;
            int step = (int) Math.sqrt(arr.size());

            while (arr.get(curr).getName().compareTo(name) < 0) {
                if (curr == last) {
                    return -1;
                }
                prev = curr;
                curr = Math.min(curr + step, last);
            }

            while (arr.get(curr).getName().compareTo(name) > 0) {
                curr = curr - 1;
                if (curr <= prev) {
                    return -1;
                }
            }

            if (arr.get(curr).getName().equals(name)) {
                return curr;
            } else {
                return -1;
            }
        }
    }*/
    //BinarySearch
    /*public int searchBinarySearch(List<Contact> arr, String name) {
            int left = 0;
            int right = arr.size() - 1;
            while (left <= right) {
                int middle = (left + right) / 2;
                if (arr.get(middle).getName().equals(name)) {
                    return middle;
                }
                if (arr.get(middle).getName().compareTo(name) > 0) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }
            return -1;
    }*/
    //QuickSort
    /*public class QuickSort implements Sortable {

        @Override
        public boolean sort(List<Contact> people, long maxTime) {
            doQuickSort(people, 0, people.size() - 1);
            return true;
        }

        private void doQuickSort(List<Contact> people, int start, int end) {
            if (start < end) {
                int pivotPoint = partition(people, start, end);
                doQuickSort(people, start, pivotPoint - 1);
                doQuickSort(people, pivotPoint + 1, end);
            }
        }

        private int partition(List<Contact> people, int start, int end) {
            String pivot = people.get(end).getName();
            int i = start - 1;
            for (int j = start; j < end; j++) {
                if (people.get(j).getName().compareTo(pivot) <= 0) {
                    i++;
                    Contact temp = people.get(i);
                    people.set(i, people.get(j));
                    people.set(j, temp);
                }
            }
            Contact temp = people.get(i + 1);
            people.set(i + 1, people.get(end));
            people.set(end, temp);
            return i + 1;
        }
    }*/

    static private void startQuickSortBinarySearch() {
        System.out.println("Start searching (quick sort + binary search)...");
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

    static private void doHashMap() {
        System.out.println("Start searching (hash table)...");
        startTime = System.currentTimeMillis();
        //create HashMap
        long startCreatingTime = System.currentTimeMillis();
        HashMap<String, Integer> map = new HashMap<>();
        for(Member number : directory) {
            map.put(number.getName(), number.getPhoneNumber());
        }
        long finishCreatingTime = System.currentTimeMillis();
        long startSearchingTime = System.currentTimeMillis();
        int count = 0;
        for (String person : find) {
            if (map.containsKey(person)) {
                count++;
            }
        }
        long finishSearchingTime = System.currentTimeMillis();
        finishTime = System.currentTimeMillis();

        System.out.printf("Found %d/%d entries. Time take: %s %s %s%n", count, count,
                convertMsToMin(startTime,finishTime), convertMsToSec(startTime, finishTime),convertMsToMs(startTime, finishTime));

        System.out.printf("Creating time: %s %s %s%n", convertMsToMin(startCreatingTime, finishCreatingTime),convertMsToSec(startCreatingTime, finishCreatingTime),convertMsToMs(startCreatingTime, finishCreatingTime));

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


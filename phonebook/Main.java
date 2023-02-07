package phonebook;

import lombok.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    static int count = 0;

    static long startTime;

    static long finishTime;

    static ArrayList<String> find = new ArrayList<>();
    static ArrayList<Member> directory = new ArrayList<>();

    static Map<Integer, Integer> hashtable = new HashMap<>();

    static final Path pathToFind = Paths.get("d:"+File.separator+"JAVA"+File.separator+"Phone Book"+File.separator+
            "Phone Book"+File.separator+"task"+File.separator+"src"+File.separator+"phonebook"+File.separator+"find.txt");

    static final Path pathToDirectory = Paths.get("d:"+File.separator+"JAVA"+File.separator+"Phone Book"+File.separator+
            "Phone Book"+File.separator+"task"+File.separator+"src"+File.separator+"phonebook"+File.separator+"directory.txt");

    public static void main(String[] args) {
        createFindList();
        createDirectory();
        startLinearSearch();
        startBJ();
        startQuickSortBinarySearch();
        startHashTable();

    }

    static private void createFindList() {
        // create list find
        try (Scanner scanner = new Scanner(pathToFind.toFile()))
        {
            do {
                find.add(scanner.nextLine());
            } while(scanner.hasNextLine());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static private void createDirectory() {
        // create list directory
        try (Scanner scan = new Scanner(pathToDirectory.toFile()))
        {
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
        System.out.printf("Found %d/%d entries. Time take: %s %s %s%n%n", count, count,
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

        System.out.printf("Searching time: %s %s %s%n%n", convertMsToMin(startSearchingTime,finishSearchingTime),convertMsToSec(startSearchingTime, finishSearchingTime),convertMsToMs(startSearchingTime, finishSearchingTime));
    }

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

    /*public static boolean sort(List<Contact> people, long maxTime) {
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

        System.out.printf("Searching time: %s %s %s%n%n", convertMsToMin(startSearchingTime,finishSearchingTime),convertMsToSec(startSearchingTime, finishSearchingTime),convertMsToMs(startSearchingTime, finishSearchingTime));
    }

    static private void startHashTable() {
        System.out.println("Start searching (hash table)...");
        //create a table;
        startTime = System.currentTimeMillis();
        long startSortingTime = System.currentTimeMillis();
        directory.forEach(x-> hashtable.put(x.getName().hashCode(), x.getPhoneNumber()));
        long finishSortingTime = System.currentTimeMillis();

        //start search
        count = 0;
        long startSearchingTime = System.currentTimeMillis();
        for(String name : find) {
            if (hashtable.getOrDefault(name.hashCode(), 0) != 0) {
                count++;
            }
        }
        long finishSearchingTime = System.currentTimeMillis();
        //create finish time in milliseconds;
        finishTime = System.currentTimeMillis();

        System.out.printf("Found %d/%d entries. Time take: %s %s %s%n", count, count,
                convertMsToMin(startTime,finishTime), convertMsToSec(startTime, finishTime),convertMsToMs(startTime, finishTime));

        System.out.printf("Creating time: %s %s %s%n", convertMsToMin(startSortingTime, finishSortingTime),convertMsToSec(startSortingTime, finishSortingTime),convertMsToMs(startSortingTime, finishSortingTime));

        System.out.printf("Searching time: %s %s %s%n%n", convertMsToMin(startSearchingTime,finishSearchingTime),convertMsToSec(startSearchingTime, finishSearchingTime),convertMsToMs(startSearchingTime, finishSearchingTime));
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

     @AllArgsConstructor
     @Getter
     @EqualsAndHashCode
     class Member implements Comparable<Member>{

        @EqualsAndHashCode.Exclude int phoneNumber;
        String name;

         @Override
         public int compareTo(Member member) {
             return this.name.compareTo(member.getName());
         }
     }


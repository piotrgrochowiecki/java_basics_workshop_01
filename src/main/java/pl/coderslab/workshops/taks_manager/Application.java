package pl.coderslab.workshops.taks_manager;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Application {

    static String[][] tasks;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        tasks = tasksReader("tasks.csv");
        optionsDisplay();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            switch (input) {
                case "add":
                    addTask();
                    break;
                case "remove":
                    removeTask(tasks, getTheNumber());
                    System.out.println("Task was successfully deleted");
                    break;
                case "list":
                    listTask(tasks);
                    break;
                case "exit":
                    saveTabToFile("tasks.csv", tasks);
                    System.out.println(ConsoleColors.RED + "Bye, bye");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Select correct option!");
            }

            optionsDisplay();

        }

    }

    public static void optionsDisplay() {
        System.out.println(ConsoleColors.BLUE + "Please select an option:");

        String[] opts = {"add", "remove", "list", "exit"};
        for (int i = 0; i < opts.length; i++) {
            System.out.println(ConsoleColors.RESET + opts[i]);
        }
    }

    public static String[][] tasksReader(String fileName) {

        Path path = Paths.get(fileName);
        if (!Files.exists(path)) {
            System.out.println("Requested file does not exist!");
            System.exit(0);
        }

        String[][] tab = null;

        try {
            List<String> strings = Files.readAllLines(path);
            tab = new String[strings.size()][strings.get(0).split(",").length];

            for (int i = 0; i < strings.size(); i++) {
                String[] split = strings.get(i).split(",");
                for (int j = 0; j < split.length; j++) {
                    tab[i][j] = split[j];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Próbowałem to zrobić samemu bez list, ale mi się nie udało:
//        File file = new File(fileName);
//        String[][] tab = null;
//        String str= " ";
//        String[] lines = new String[3];
//
//        try {
//            Scanner scanner = new Scanner(file);
//            while (scanner.hasNextLine()) {
//                for (int i = 0; i < lines.length; i++) {
//                    lines[i] = scanner.nextLine();
//                }
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("Requested file does not exist!");
//        }
//
//        System.out.println(Arrays.toString(lines));

        return tab;
    }

    public static void addTask() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please add task description: ");
        String description = scanner.nextLine();

        System.out.println("Please add due date (YYYY-MM-DD): ");
        String dueDate = scanner.nextLine();

        System.out.println("Is your task important (true/false)? ");
        String importance = scanner.nextLine();

        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = new String[3];
        tasks[tasks.length - 1][0] = description;
        tasks[tasks.length - 1][1] = " " + dueDate;
        tasks[tasks.length - 1][2] = " " + importance;
    }

    public static void listTask(String[][] tab) {

        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                System.out.print(tab[i][j] + " ");
            }
            System.out.println();
        }
    }

//    public static int removeTask(String input) {
//        String[][] tasks = Methods.tasksReader("tasks.csv");
//
//        System.out.println("Please select task number to be removed: ");
//        Scanner scanner = new Scanner(System.in);
//
//
//        while (!scanner.hasNextInt()) {
//            scanner.next();
//            System.out.println("Please provide a number! ");
//            while (!(scanner.nextInt() <= 0)) {
//                scanner.next();
//                System.out.println("Number has to be greater than zero! ");
//            }
//        }
//
//        int number = scanner.nextInt();
//
//        try {
//            if (number < tab.length) {
//                tasks = ArrayUtils.remove(tab, number);
//            }
//        } catch (ArrayIndexOutOfBoundsException e) {
//            System.out.println("Task with provided number does not exist!");
//        }
//        return -1;
//    }

    public static boolean isNumberGreaterEqualZero(String input) {
        if (NumberUtils.isParsable(input)) {
            return Integer.parseInt(input) >= 0;
        }
        return false;
    }

    public static int getTheNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select task to remove: ");

        String n = scanner.nextLine();
        while (!isNumberGreaterEqualZero(n)) {
            System.out.println("Incorrect argument. Task has a positive number! ");
            scanner.nextInt();
        }
        return Integer.parseInt(n);
    }

    public static void removeTask(String[][] tab, int index) {
        try {
            if (index < tab.length ) {
                tasks = ArrayUtils.remove(tab, index - 1);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Element does not exist in the tab!");
        }
    }

    public static void saveTabToFile(String fileName, String[][] tab) {
        Path dir = Paths.get(fileName);

        String[] lines = new String[tasks.length];
        for (int i = 0; i < tab.length; i++) {
            lines[i] = String.join(",", tab[i]);
        }

        try {
            Files.write(dir, Arrays.asList(lines));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

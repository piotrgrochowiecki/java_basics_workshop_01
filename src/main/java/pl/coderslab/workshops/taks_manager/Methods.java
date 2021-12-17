package pl.coderslab.workshops.taks_manager;

import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Methods {

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

        /* Próbowałem to zrobić samemu bez list, ale mi się nie udało:
        File file = new File(fileName);
        String[][] tab = null;
        String str= " ";
        String[] lines = new String[3];

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                for (int i = 0; i < lines.length; i++) {
                    lines[i] = scanner.nextLine();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Requested file does not exist!");
        }

        System.out.println(Arrays.toString(lines));


         */

        return tab;
    }


    public static void taskChoice() {
        Scanner scanner = new Scanner(System.in);

        switch (scanner.next()) {
            case "add":
                addTask();
                break;
            case "remove":
                removeTask(tasksReader("tasks.csv"));
                break;
            case "list":
                listTask(tasksReader("tasks.csv"));
                break;
            case "exit":
                //exitTask();
                break;
            default:
                System.out.println("Select correct option!");
        }
    }

    public static void addTask() {
        String[][] tasks = Methods.tasksReader("tasks.csv");
        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please add task description: ");
        String description = scanner.nextLine();


        System.out.println("Please add due date (YYYY-MM-DD): ");
        String dueDate = scanner.nextLine();


        System.out.println("Is your task important (true/false)? ");
        String importance = scanner.nextLine();

        tasks[tasks.length - 1] = new String[3];
        tasks[tasks.length - 1][0] = description;
        tasks[tasks.length - 1][1] = dueDate;
        tasks[tasks.length - 1][2] = importance;
    }

    public static void listTask(String[][] tab) {

        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                System.out.print(tab[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void removeTask(String[][] tab) {
        String[][] tasks = Methods.tasksReader("tasks.csv");

        System.out.println("Please select task number to be removed: ");
        Scanner scanner = new Scanner(System.in);


        while (!scanner.hasNextInt()) { //czy da się tu w jednej linijce zapisać warunek bycia liczbą oraz większe lub równe 0? Bo nie chcę kopiować wprost z rozwiązania.
            scanner.next();
            System.out.println("Number has to be equal or greater than 0!");
            System.out.println("Please select task to be removed: ");
        }

        int number = scanner.nextInt();

        try {
            if (number < tab.length) {
                tasks = ArrayUtils.remove(tab, number);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Task with provided number does not exist!");
        }

    }


}

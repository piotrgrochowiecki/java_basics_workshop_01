package pl.coderslab.workshops.taks_manager;

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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][] tasks = Methods.tasksReader("tasks.csv");

        switch (scanner.next()) {
            case "add":
                Methods.addTask();
                break;
            case "remove":
                Methods.removeTask(tasks, Methods.getTheNumber());
                System.out.println("Task was succesfully deleted");
                break;
            case "list":
                Methods.listTask(tasks);
                break;
            case "exit":
                Methods.saveTabToFile("tasks.csv", tasks);
                System.out.println(ConsoleColors.RED + "Bye, bye");
                break;
            default:
                System.out.println("Select correct option!");
        }
    }
}

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
        Methods.optionsDisplay();
        String[][] tasks = Methods.tasksReader("tasks.csv");
        Methods.taskChoice();
    }

}

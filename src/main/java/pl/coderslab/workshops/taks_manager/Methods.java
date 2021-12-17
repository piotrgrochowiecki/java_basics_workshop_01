package pl.coderslab.workshops.taks_manager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Methods {

    public static void optionsDisplay() {
        System.out.println(ConsoleColors.BLUE + "Please select an option:");

        String[] opts = {"add", "remove", "list", "exit"};
        for (int i = 0; i < opts.length; i++) {
            System.out.println(ConsoleColors.WHITE_BRIGHT + opts[i]);
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

        switch (scanner) {
            case "add":
                //addTask();
                break;
            case "remove":
                //removeTask();
                break;
            case "list":
                //listTask();
                break;
            case "exit":
                //exitTask();
                break;
            default:
                System.out.println("Select correct option!");
        }
    }
}

package pl.coderslab.workshops.taks_manager;

public class Options {

    public static void optionsDisplay() {
        System.out.println(ConsoleColors.BLUE + "Please select an option:");

        String[] opts = {"add", "remove", "list", "exit"};
        for (int i = 0; i < opts.length; i++) {
            System.out.println(ConsoleColors.WHITE_BRIGHT + opts[i]);
        }
    }
}

import java.io.File;
import java.util.Scanner;
import java.lang.System;

public class App {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        Table table = new Table();
        boolean again = true;

        while (again) {
            table.clearScreen();
            table.mainMenu();
            again = false;

            System.out.println("\nChoose an option. (Type '0' for help)");
            int answer = scanner.nextInt();

            switch (answer) {
                case 0:
                    table.clearScreen();
                    table.helpMenu();
                    System.out.println("Press any key to return");
                    break;
                case 1:
                    table.clearScreen();
                    // File name for storing tasks
                    String filename = "tasks.csv";
                    // Check if the file exists
                    File file = new File(filename);
                    if (file.exists() && !file.isDirectory()) {
                        // Load tasks from the CSV file
                        taskManager.loadTasks(filename);
                    } else {
                        System.out.println("No existing task file found.");
                    }
                    // Check if tasks are loaded, if not, prompt user to add a task
                    if (taskManager.getTasks().isEmpty()) {
                        System.out.println("No tasks available. Would you like to add a task? (yes/no)");
                        String response = scanner.nextLine().trim().toLowerCase();
                        if ("yes".equals(response)) {
                            taskManager.addTask();
                        }
                    }
                    // Displaying initial tasks
                    System.out.println("Initial Tasks:");
                    taskManager.displayTasks();
                    again = true;
                    break;
                case 2:
                    table.clearScreen();
                    table.addTaskScreen();
                    taskManager.addTask();
                    again = true;
                    break;
                case 3:
                    table.clearScreen();
                    again = false;
                    break;
                default:
                    table.clearScreen();
                    again = true;
                    break;
            }

        }
        taskManager.saveTasks("tasks.csv");
        System.out.println("\nTasks saved to tasks.csv");
        // Rest of your main method logic goes here

        // Closing scanner
        scanner.close();

    }
}

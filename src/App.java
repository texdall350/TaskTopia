import java.io.IOException;
import java.util.Scanner;
import java.lang.System;

public class App {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        String filename = "tasks.csv";
        // File file = new File(filename);
        // Load tasks from the CSV file
        taskManager.loadTasks(filename);

        Scanner scanner = new Scanner(System.in);
        // File name for storing tasks

        Table table = new Table();
        boolean again = true;

        while (again) {
            table.clearScreen();
            table.mainMenu();
            again = false;

            System.out.println("\nChoose an option. (Type '0' for help)");
            int answer = scanner.nextInt();
            scanner.nextLine();

            switch (answer) {
                case 0:
                    table.clearScreen();
                    table.helpMenu();
                    System.out.println("Press any key to return");
                    // Wait for user to press any key
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    again = true;
                    break;

                case 1:
                    table.clearScreen();
                    // Check if tasks are loaded, if not, prompt user to add a task
                    if (taskManager.getTasks().isEmpty()) {
                        table.noTasksScreen();
                        String response = scanner.nextLine().trim().toLowerCase();
                        if ("yes".equals(response)) {
                            table.addTaskScreen();
                            taskManager.addTask();
                            scanner.nextLine();
                        }
                    } else {
                        table.clearScreen();
                        table.sortingScreen();
                        int response = scanner.nextInt();
                        switch (response) {
                            case 1:
                                taskManager.sortTasksByDueDate();
                                break;
                            case 2:
                                taskManager.sortTasksByPriority();
                                break;
                            case 3:
                                taskManager.sortTasksByType();
                                break;
                            default:
                                break;
                        }
                        scanner.nextLine();

                    }
                    // Displaying initial tasks
                    table.clearScreen();
                    table.titleMenu();
                    table.blankLine();
                    table.lastLine();
                    taskManager.displayTasks();
                    System.out.println("Press any key to return");
                    // Wait for user to press any key
                    try {

                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    again = true;
                    break;

                case 2:
                    table.clearScreen();
                    table.addTaskScreen();
                    taskManager.addTask();
                    again = true;
                    taskManager.saveTasks("tasks.csv");
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
        // close file

        // Closing scanner
        scanner.close();

    }
}

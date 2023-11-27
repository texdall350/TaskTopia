
/* CSE 1325-005

1.	Robert (Tres) Eberle 	0000000000 
2.	Joseph Hite	            0000000000
3.	Gerardo Salinas	        0000000000 
4.	Jaime Zhang	            1001982072
5.	Dallas Spellman	        1002112847

TaskTopia - A task tracker and orginizer
Java version 17 or higher required
*/

import java.io.IOException;
import java.util.Scanner;
import java.lang.System;

public class App {
    public static void main(String[] args) {
        // Initialize TaskManager for managing tasks
        TaskManager taskManager = new TaskManager();
        TextColors TEXT = new TextColors();
        String filename = "tasks.csv";

        // Load tasks from the CSV file at the start of the program
        taskManager.loadTasks(filename);

        Scanner scanner = new Scanner(System.in);

        Table table = new Table();
        boolean again = true;

        // Main loop to display the menu and handle user input
        while (again) {
            table.clearScreen();
            table.mainMenu();
            again = false;

            System.out.println("\nChoose an option. (Type '0' for help)");
            int answer = scanner.nextInt();
            scanner.nextLine();

            // Switch case to handle different menu options
            switch (answer) {
                case 0:
                    // Help menu option
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
                    // Option to view and sort tasks
                    table.clearScreen();
                    if (taskManager.getTasks().isEmpty()) {
                        // No tasks available, prompt user to add a task
                        table.noTasksScreen();
                        String response = scanner.nextLine().trim().toLowerCase();
                        if ("yes".equals(response)) {
                            table.clearScreen();
                            table.addTaskScreen();
                            taskManager.addTask();
                            scanner.nextLine();
                        }
                    } else {
                        // Display sorting options for tasks
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
                    // Display tasks after sorting
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
                    // Option to add a new task
                    table.clearScreen();
                    table.addTaskScreen();
                    taskManager.addTask();
                    again = true;
                    break;

                case 3:
                    // Option to edit, mark as completed, or remove tasks
                    table.clearScreen();
                    table.editScreen();
                    int response = scanner.nextInt();
                    switch (response) {
                        case 1:
                            // Mark a task as completed
                            table.clearScreen();
                            table.titleMenu();
                            table.lastLine();
                            taskManager.markTaskAsCompleted();
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
                            // Remove a task
                            table.clearScreen();
                            table.titleMenu();
                            table.lastLine();
                            taskManager.removeTask();
                            System.out.println("Press any key to return");
                            // Wait for user to press any key
                            try {
                                System.in.read();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            again = true;
                            break;
                        case 3:
                            // Edit a task
                            table.clearScreen();
                            table.titleMenu();
                            table.lastLine();
                            taskManager.editTask();
                            System.out.println("Press any key to return");
                            // Wait for user to press any key
                            try {
                                System.in.read();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            again = true;
                            break;
                        default:
                            break;
                    }
                    again = true;
                    break;

                case 4:
                    // Exit the program
                    table.clearScreen();
                    again = false;
                    break;

                default:
                    // Default case to handle invalid inputs
                    table.clearScreen();
                    again = true;
                    break;
            }
        }

        // Save tasks to CSV file before exiting the program
        taskManager.saveTasks("tasks.csv");
        System.out.println(TEXT.GREEN + "\nTasks saved to tasks.csv" + TEXT.RESET);

        // Close the scanner before exiting
        scanner.close();
    }
}

import java.io.File;
import java.util.Scanner;
import  java.lang.System;

public class App 
{
    public static void main(String[] args) 
    {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        Table table = new Table();
        boolean again = true;


        table.mainMenu();

        while (again)
        {
            
            again = false;

            
            System.out.println("\nChoose an option. Type 'help' for help");
            String answer = scanner.nextLine();
           

            switch(answer.toLowerCase())
            {
                case "help":
                    System.out.print("\nType any of the following options:\n");
                    System.out.println("View Tasks - To view or make new tasks");
                    System.out.println("Exit - exits program");
                    again = true;
                    break;
                case "exit":
                    System.exit(0);
                    break;
                case "view tasks":
                    // File name for storing tasks
                    String filename = "tasks.csv";

                    // Check if the file exists
                    File file = new File(filename);

                    if (file.exists() && !file.isDirectory()) 
                    {
                        // Load tasks from the CSV file
                        taskManager.loadTasks(filename);
                    } 
                    else 
                    {
                        System.out.println("No existing task file found.");
                    }

                    // Check if tasks are loaded, if not, prompt user to add a task
                    if (taskManager.getTasks().isEmpty()) 
                    {
                        System.out.println("No tasks available. Would you like to add a task? (yes/no)");
                        String response = scanner.nextLine().trim().toLowerCase();
                        if ("yes".equals(response)) 
                        {
                            taskManager.addTask();
                        }
                    }

                    // Displaying initial tasks
                    System.out.println("Initial Tasks:");
                    taskManager.displayTasks();

                    taskManager.saveTasks("tasks.csv");
                    System.out.println("\nTasks saved to tasks.csv");

                    // Rest of your main method logic goes here

                    // Closing scanner
                    scanner.close();
                    break;
        }
        } 
        

    
    }
}

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

// TaskManager class manages task operations like adding, editing, and displaying tasks.
public class TaskManager {
    private List<Task> tasks;
    private Scanner scanner;
    Table taskTable = new Table();
    TextColors TEXT = new TextColors();

    // Constructor initializes the task list and scanner.
    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Returns the list of tasks.
    public List<Task> getTasks() {
        return tasks;
    }

    // Loads tasks from a CSV file using the CSVUtil class.
    public void loadTasks(String filename) {
        this.tasks = CSVUtil.loadTasks(filename);
    }

    // Saves tasks to a CSV file using the CSVUtil class.
    public void saveTasks(String filename) {
        CSVUtil.saveTasks(tasks, filename);
    }

    // Method to add a new task based on user input.
    public void addTask() {
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline
        taskTable.clearScreen();
        taskTable.titleMenu();
        System.out.print("|");
        System.out.print("_".repeat(taskTable.getSpace()));
        System.out.println("|\n");

        // Switch case to handle different types of task additions.
        switch (choice) {
            // Cases for adding different types of tasks.
            case 1:
                System.out.println("New Basic Task: ");
                addBasicTask();
                break;
            case 2:
                System.out.println("New Work Task: ");
                addWorkTask();
                break;
            case 3:
                System.out.println("New School Task: ");
                addSchoolTask();
                break;
            case 4:
                System.out.println("New Personal Task: ");
                addPersonalTask();
                break;
            case 5:
                System.out.println("New Household Chore: ");
                addHouseholdChore();
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
                break;
        }
    }

    // Method to get common details for all task types.
    private TaskDetails getCommonTaskDetails() {
        System.out.print("\n\tEnter task title: ");
        String title = scanner.nextLine();

        // Set the priority level for the task.
        String priority = prioritySetLevel();

        System.out.print("\n\tEnter task description: ");
        String description = scanner.nextLine();

        // Parse and set the due date for the task.
        LocalDate dueDate = parseDate();

        // Return a new TaskDetails object with the gathered information.
        return new TaskDetails(title, priority, description, dueDate);
    }

    // Method to add a basic task.
    private void addBasicTask() {
        // Get common task details.
        TaskDetails details = getCommonTaskDetails();

        // Create a new basic task with the details.
        Task basicTask = new Task(details.title, details.priority, details.description, details.dueDate);

        // Add the new task to the task list.
        tasks.add(basicTask);
        System.out.println("Basic task added successfully! ");
    }

    // Method to add a work-related task.
    private void addWorkTask() {
        // Get common task details.
        TaskDetails details = getCommonTaskDetails();

        System.out.print("\n\tEnter project: ");
        String project = scanner.nextLine();

        // Create a new work task with the details and additional project information.
        WorkTask workTask = new WorkTask(details.title, details.priority, details.description, details.dueDate, project,
                parseTime());

        // Add the new work task to the task list.
        tasks.add(workTask);
        System.out.println("Work task added successfully!");
    }

    // Method to add a school-related task.
    private void addSchoolTask() {
        // Get common task details.
        TaskDetails details = getCommonTaskDetails();

        System.out.print("\n\tEnter subject: ");
        String subject = scanner.nextLine();
        System.out.print("\n\tEnter assignment type: ");
        String assignmentType = scanner.nextLine();

        // Create a new school task with the details and additional school-related
        // information.
        SchoolTask schoolTask = new SchoolTask(details.title, details.priority, details.description, details.dueDate,
                subject, assignmentType);

        // Add the new school task to the task list.
        tasks.add(schoolTask);
        System.out.println("School task added successfully!");
    }

    // Method to add a personal task.
    private void addPersonalTask() {
        // Get common task details.
        TaskDetails details = getCommonTaskDetails();

        System.out.print("\n\tEnter category: ");
        String category = scanner.nextLine();
        System.out.print("\n\tEnter location: ");
        String location = scanner.nextLine();

        // Create a new personal task with the details and additional personal
        // information.
        PersonalTask personalTask = new PersonalTask(details.title, details.priority, details.description,
                details.dueDate, category, location);

        // Add the new personal task to the task list.
        tasks.add(personalTask);
        System.out.println("Personal task added successfully!");
    }

    // Method to add a household chore.
    private void addHouseholdChore() {
        // Get common task details.
        TaskDetails details = getCommonTaskDetails();

        System.out.print("\n\tEnter room: ");
        String room = scanner.nextLine();
        System.out.print("\n\tEnter equipment needed: ");
        String equipmentNeeded = scanner.nextLine();

        // Create a new household chore with the details and additional chore-related
        // information.
        HouseholdChores householdChore = new HouseholdChores(details.title, details.priority, details.description,
                details.dueDate, room, equipmentNeeded);

        // Add the new household chore to the task list.
        tasks.add(householdChore);
        System.out.println("Household chore added successfully!");
    }

    // Parses and returns a LocalTime object from user input.
    private LocalTime parseTime() {
        LocalTime time = null;

        while (true) {
            System.out.print("\n\tEnter deadline time (h:mm AM/PM): ");
            String timeString = scanner.nextLine();

            try {
                // Define the time format for parsing.
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
                // Parse the time string into a LocalTime object.
                time = LocalTime.parse(timeString, timeFormatter);
                break;
            } catch (DateTimeParseException e) {
                // Handle invalid time format input.
                System.out
                        .println(TEXT.RED + "***Invalid time format. Please use 'h:mm AM/PM' format.***" + TEXT.RESET);
            }
        }

        return time;
    }

    // Parses and returns a LocalDate object from user input.
    private LocalDate parseDate() {
        LocalDate date = null;

        while (true) {
            System.out.print("\n\tEnter due date (YYYY-MM-DD): ");
            String dateString = scanner.nextLine();

            try {
                // Parse the date string into a LocalDate object.
                date = LocalDate.parse(dateString);
                break;
            } catch (DateTimeParseException e) {
                // Handle invalid date format input.
                System.out
                        .println(TEXT.RED + "***Invalid date format. Please use 'YYYY-MM-DD' format.***" + TEXT.RESET);
            }
        }

        return date;
    }

    // Displays all tasks in the task list.
    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (Task task : tasks) {
                // Print each task.
                System.out.println(task + "\n");
            }
        }
    }

    // Sorts tasks by due date.
    public void sortTasksByDueDate() {
        // Sort tasks in ascending order of their due dates.
        tasks.sort(Comparator.comparing(Task::getDueDate));
    }

    // Sorts tasks by priority.
    public void sortTasksByPriority() {
        // Define a comparator for sorting tasks by priority.
        Comparator<Task> priorityComparator = Comparator
                .comparingInt(obj -> obj.priority.equals(TEXT.RED + "High" + TEXT.RESET) ? 0
                        : obj.priority.equals(TEXT.YELLOW + "Normal" + TEXT.RESET) ? 1 : 2);
        // Sort tasks based on the defined priority levels.
        Collections.sort(tasks, priorityComparator);
    }

    // Sorts tasks by type.
    public void sortTasksByType() {
        // Define a comparator for sorting tasks by their type.
        Comparator<Task> typeComparator = Comparator
                .comparingInt(obj -> obj.getClass().getSimpleName().equals("Task") ? 0
                        : obj.getClass().getSimpleName().equals("WorkTask") ? 1
                                : obj.getClass().getSimpleName().equals("SchoolTask") ? 2
                                        : obj.getClass().getSimpleName().equals("PersonalTask") ? 3 : 4);
        // Sort tasks based on their type.
        Collections.sort(tasks, typeComparator);
    }

    // Marks a task as completed based on user input.
    public void markTaskAsCompleted() {
        System.out.println("\nList of all your tasks:\n");
        for (Task task : tasks) {
            System.out.println("\t" + task.getTitle());
        }
        System.out.print("\nEnter the title of the task to mark as completed from the list above: ");
        String title = scanner.nextLine();

        Task foundTask = findTaskByTitle(title);
        while (true) {
            if (foundTask != null) {
                System.out.println(TEXT.GREEN + "Task marked as completed!" + TEXT.RESET);
                // Mark the found task as completed.
                foundTask.setCompleted(true);
                break;
            } else {
                // Handle case where task is not found.
                System.out.println(TEXT.RED + "Task not found." + TEXT.RESET);
                System.out.println("Please input valid task name: ");
                title = scanner.nextLine();
                foundTask = findTaskByTitle(title);
            }
        }
    }

    // Removes a task based on user input.
    public void removeTask() {
        System.out.println("\nList of all your tasks:\n");
        for (Task task : tasks) {
            System.out.println("\t" + task.getTitle());
        }
        System.out.print("\nEnter the title of the task to remove from the list above: ");
        String title = scanner.nextLine();

        Task taskToRemove = findTaskByTitle(title);

        while (true) {
            if (taskToRemove != null) {
                System.out.println(TEXT.GREEN + "Task removed successfully!" + TEXT.RESET);
                // Remove the specified task from the list.
                tasks.remove(taskToRemove);
                break;
            } else {
                // Handle case where task is not found.
                System.out.println(TEXT.RED + "Task not found." + TEXT.RESET);
                System.out.println("Please input valid task name: ");
                title = scanner.nextLine();
                taskToRemove = findTaskByTitle(title);
            }
        }
    }

    // Allows the user to edit an existing task.
    public void editTask() {
        System.out.println("\nList of all your tasks:\n");
        // Display all task titles.
        for (Task task : tasks) {
            System.out.println("\t" + task.getTitle());
        }
        System.out.print("\nEnter the title of the task to edit from the list above: ");
        String title = scanner.nextLine();

        // Find the task by title.
        Task taskToEdit = findTaskByTitle(title);

        // Loop until a valid task is found.
        while (taskToEdit == null) {
            System.out.println(TEXT.RED + "Task not found." + TEXT.RESET);
            System.out.println("Please input valid task name: ");
            title = scanner.nextLine();
            taskToEdit = findTaskByTitle(title);
        }

        boolean continueEditing = true;
        while (continueEditing) {
            // Display current task details.
            System.out.println("\n" + taskToEdit);
            System.out.print("\nPlease enter the variable you would like to edit (or type 'exit' to finish): ");
            String variableToEdit = scanner.nextLine();

            // Exit the editing loop if 'exit' is entered.
            if ("exit".equalsIgnoreCase(variableToEdit)) {
                continueEditing = false;
                break;
            }

            // Handle editing based on the variable chosen.
            switch (variableToEdit) {
                // Cases for editing different attributes of the task.
                // Each case updates the respective attribute and prints a success message.
                case "Title":
                    System.out.print("Please enter new title for your task: ");
                    String newTitle = scanner.nextLine();
                    taskToEdit.setTitle(newTitle);
                    System.out.println(TEXT.GREEN + "Successfully edited title!" + TEXT.RESET);
                    break;

                case "Priority":
                    String newPriority = prioritySetLevel();
                    taskToEdit.setPriority(newPriority);
                    System.out.println(TEXT.GREEN + "Successfully edited priority level!" + TEXT.RESET);
                    break;

                case "Description":
                    System.out.print("Please enter new description for your task: ");
                    String newDescription = scanner.nextLine();
                    taskToEdit.setDescription(newDescription);
                    System.out.println(TEXT.GREEN + "Successfully edited description!" + TEXT.RESET);
                    break;

                case "Due Date":
                    System.out.print("Please enter new due date for your task: ");
                    LocalDate newDueDate = parseDate();
                    taskToEdit.setDueDate(newDueDate);
                    System.out.println(TEXT.GREEN + "Successfully edited due date!" + TEXT.RESET);
                    break;

                case "Complete":
                    System.out.print("Is this task complete? [Y/N]: ");
                    String yesOrNo = scanner.nextLine();
                    Boolean newComplete;
                    if (yesOrNo.equalsIgnoreCase("Y")) {
                        newComplete = true;
                    } else {
                        newComplete = false;
                    }
                    taskToEdit.setCompleted(newComplete);
                    System.out.println(TEXT.GREEN + "Successfully edited completion status!" + TEXT.RESET);
                    break;

                case "Subject":
                    System.out.print("Please enter new subject for your task: ");
                    String newSubject = scanner.nextLine();
                    ((SchoolTask) taskToEdit).setSubject(newSubject);
                    System.out.println(TEXT.GREEN + "Successfully edited subject!" + TEXT.RESET);
                    break;

                case "Assignment":
                    System.out.print("Please enter new assignment for your task: ");
                    String newAssignment = scanner.nextLine();
                    ((SchoolTask) taskToEdit).setAssignmentType(newAssignment);
                    System.out.println(TEXT.GREEN + "Successfully edited subject!" + TEXT.RESET);
                    break;

                case "Project":
                    System.out.print("Please enter new project for your task: ");
                    String newProject = scanner.nextLine();
                    ((WorkTask) taskToEdit).setProject(newProject);
                    System.out.println(TEXT.GREEN + "Successfully edited project name!" + TEXT.RESET);
                    break;

                case "Deadline Time":
                    LocalTime newTime = parseTime();
                    ((WorkTask) taskToEdit).setDeadlineTime(newTime);
                    System.out.println(TEXT.GREEN + "Successfully edited due date!" + TEXT.RESET);
                    break;

                case "Category":
                    System.out.print("Please enter new category for your task: ");
                    String newCategory = scanner.nextLine();
                    ((PersonalTask) taskToEdit).setCategory(newCategory);
                    System.out.println(TEXT.GREEN + "Successfully edited category!" + TEXT.RESET);
                    break;

                case "Location":
                    System.out.print("Please enter new location for your task: ");
                    String newLocation = scanner.nextLine();
                    ((PersonalTask) taskToEdit).setLocation(newLocation);
                    System.out.println(TEXT.GREEN + "Successfully edited location!" + TEXT.RESET);
                    break;

                case "Room":
                    System.out.print("Please enter new room for your household chore: ");
                    String newRoom = scanner.nextLine();
                    ((HouseholdChores) taskToEdit).setRoom(newRoom);
                    System.out.println(TEXT.GREEN + "Successfully edited room!" + TEXT.RESET);
                    break;

                case "Equipment Needed":
                    System.out.print("Please enter new equipment needed for your household chore: ");
                    String newEquipmentNeeded = scanner.nextLine();
                    ((HouseholdChores) taskToEdit).setEquipmentNeeded(newEquipmentNeeded);
                    System.out.println(TEXT.GREEN + "Successfully edited equipment needed!" + TEXT.RESET);
                    break;
                default:
                    System.out.println(TEXT.RED + "Variable not found." + TEXT.RESET);
                    System.out.println("Please input valid variable name: ");
                    break;
            }
        }
    }

    // Finds and returns a task by its title.
    private Task findTaskByTitle(String title) {
        // Iterate through the task list.
        for (Task task : tasks) {
            // Check if the task title matches the provided title (case-insensitive).
            if (task.getTitle().equalsIgnoreCase(title)) {
                return task; // Return the found task.
            }
        }
        return null; // Return null if no matching task is found.
    }

    public String priorityColorSet(String priority) {
        priority = priority.toLowerCase();
        // Check for different priority levels and assign the corresponding color-coded
        // priority.
        if (priority.contains("high")) {
            priority = TEXT.RED + "High" + TEXT.RESET;
        } else if (priority.contains("low")) {
            priority = TEXT.GREEN + "Low" + TEXT.RESET;
        } else if (priority.contains("normal")) {
            priority = TEXT.YELLOW + "Normal" + TEXT.RESET;
        } else {
            return null;
        }
        return priority;
    }

    // Sets the priority level for a task based on user input.
    public String prioritySetLevel() {
        // Prompt the user to enter a priority level.
        System.out.print("\n\tEnter priority (" + TEXT.RED + "High," + TEXT.GREEN + " Low," + TEXT.YELLOW + " Normal"
                + TEXT.RESET + "): ");
        String priority = scanner.nextLine();

        // Loop until a valid priority is entered.
        while (true) {

            priority = priorityColorSet(priority);
            if (priority == null) {
                // If an invalid priority is entered, prompt the user again.
                System.out.println(TEXT.RED + "\tINVALID PRIORITY LEVEL!" + TEXT.RESET);
                System.out.print(
                        "\tEnter priority (" + TEXT.RED + "High," + TEXT.GREEN + " Low," + TEXT.YELLOW + " Normal"
                                + TEXT.RESET + "): ");
                priority = scanner.nextLine();
            } else
                break;
        }
        return priority; // Return the formatted priority string.
    }

}

// Class representing the details of a task.
class TaskDetails {
    String title; // Title of the task.
    String priority; // Priority level of the task.
    String description; // Description of the task.
    LocalDate dueDate; // Due date of the task.

    // Constructor to initialize the task details.
    public TaskDetails(String title, String priority, String description, LocalDate dueDate) {
        this.title = title;
        this.priority = priority;
        this.description = description;
        this.dueDate = dueDate;
    }
}

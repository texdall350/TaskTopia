import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private List<Task> tasks;
    private Scanner scanner;

    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void loadTasks(String filename) {
        this.tasks = CSVUtil.loadTasks(filename);
    }

    public void saveTasks(String filename) {
        CSVUtil.saveTasks(tasks, filename);
    }

    public void addTask() {
        Table taskTable = new Table();
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline
        taskTable.clearScreen();
        taskTable.titleMenu();
        System.out.print("|");
        System.out.print("_".repeat(taskTable.getSpace()));
        System.out.println("|\n");

        switch (choice) {
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

    private TaskDetails getCommonTaskDetails() {
        System.out.print("\n\tEnter task title:");
        String title = scanner.nextLine();
        System.out.print("\n\tEnter priority (High, Low, Normal):");
        String priority = scanner.nextLine();
        System.out.print("\n\tEnter task description:");
        String description = scanner.nextLine();
        System.out.print("\n\tEnter due date (YYYY-MM-DD):");
        LocalDate dueDate = LocalDate.parse(scanner.nextLine());

        return new TaskDetails(title, priority, description, dueDate);
    }

    private void addBasicTask() {
        TaskDetails details = getCommonTaskDetails();
        Task basicTask = new Task(details.title, details.priority, details.description, details.dueDate);
        tasks.add(basicTask);
        System.out.println("Basic task added successfully!");
    }

    private void addWorkTask() {
        TaskDetails details = getCommonTaskDetails();
        System.out.print("\n\tEnter project:");
        String project = scanner.nextLine();
        WorkTask workTask = new WorkTask(details.title, details.priority, details.description, details.dueDate, project,
                parseTime());
        tasks.add(workTask);
        System.out.println("Work task added successfully!");
    }

    private void addSchoolTask() {
        TaskDetails details = getCommonTaskDetails();
        System.out.print("\n\tEnter subject:");
        String subject = scanner.nextLine();
        System.out.print("\n\tEnter assignment type:");
        String assignmentType = scanner.nextLine();
        SchoolTask schoolTask = new SchoolTask(details.title, details.priority, details.description, details.dueDate,
                subject, assignmentType);
        tasks.add(schoolTask);
        System.out.println("School task added successfully!");
    }

    private void addPersonalTask() {
        TaskDetails details = getCommonTaskDetails();
        System.out.print("\n\tEnter category:");
        String category = scanner.nextLine();
        System.out.print("\n\tEnter location:");
        String location = scanner.nextLine();
        PersonalTask personalTask = new PersonalTask(details.title, details.priority, details.description,
                details.dueDate, category, location);
        tasks.add(personalTask);
        System.out.println("Personal task added successfully!");
    }

    private void addHouseholdChore() {
        TaskDetails details = getCommonTaskDetails();
        System.out.print("\n\tEnter room:");
        String room = scanner.nextLine();
        System.out.print("\n\tEnter equipment needed:");
        String equipmentNeeded = scanner.nextLine();
        HouseholdChores householdChore = new HouseholdChores(details.title, details.priority, details.description,
                details.dueDate, room,
                equipmentNeeded);
        tasks.add(householdChore);
        System.out.println("Household chore added successfully!");
    }

    private LocalTime parseTime() {
        LocalTime time = null;

        while (true) {
            System.out.print("\n\tEnter deadline time (h:mm AM/PM):");
            String timeString = scanner.nextLine();

            try {
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
                time = LocalTime.parse(timeString, timeFormatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("***Invalid time format. Please use 'h:mm AM/PM' format.***");
            }
        }

        return time;
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }
}

class TaskDetails {
    String title;
    String priority;
    String description;
    LocalDate dueDate;

    public TaskDetails(String title, String priority, String description, LocalDate dueDate) {
        this.title = title;
        this.priority = priority;
        this.description = description;
        this.dueDate = dueDate;
    }
}

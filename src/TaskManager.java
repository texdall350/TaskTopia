import java.time.LocalDate;
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
        System.out.println("Choose task type: \n1. Basic Task\n2. Work Task\n3. School Task\n4. Personal Task\n5. Household Chore");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        switch (choice) {
            case 1:
                addBasicTask();
                break;
            case 2:
                addWorkTask();
                break;
            // case 3:
            //     addSchoolTask();
            //     break;
            // case 4:
            //     addPersonalTask();
            //     break;
            // case 5:
            //     addHouseholdChore();
            //     break;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
                break;
        }
    }

    private void addBasicTask() {
        System.out.println("Enter task title:");
        String title = scanner.nextLine();
        System.out.println("Enter priority:");
        String priority = scanner.nextLine();
        System.out.println("Enter task description:");
        String description = scanner.nextLine();
        System.out.println("Enter due date (YYYY-MM-DD):");
        LocalDate dueDate = LocalDate.parse(scanner.nextLine());
        Task basicTask = new Task(title, priority, description, dueDate);
        tasks.add(basicTask);
        System.out.println("Basic task added successfully!");
    }
    
    private void addWorkTask() {
        System.out.println("Enter task title:");
        String title = scanner.nextLine();
        System.out.println("Enter priority:");
        String priority = scanner.nextLine();
        System.out.println("Enter task description:");
        String description = scanner.nextLine();
        System.out.println("Enter due date (YYYY-MM-DD):");
        LocalDate dueDate = LocalDate.parse(scanner.nextLine());
        System.out.println("Enter project:");
        String project = scanner.nextLine();
        System.out.println("Enter deadline time:");
        String deadlineTime = scanner.nextLine();
        WorkTask workTask = new WorkTask(title, priority, description, dueDate, project, deadlineTime);
        tasks.add(workTask);
        System.out.println("Work task added successfully!");
    }
    
    private void addSchoolTask() {
        System.out.println("Enter task title:");
        String title = scanner.nextLine();
        System.out.println("Enter priority:");
        String priority = scanner.nextLine();
        System.out.println("Enter task description:");
        String description = scanner.nextLine();
        System.out.println("Enter due date (YYYY-MM-DD):");
        LocalDate dueDate = LocalDate.parse(scanner.nextLine());
        System.out.println("Enter subject:");
        String subject = scanner.nextLine();
        System.out.println("Enter assignment type:");
        String assignmentType = scanner.nextLine();
        SchoolTask schoolTask = new SchoolTask(title, priority, description, dueDate, subject, assignmentType);
        tasks.add(schoolTask);
        System.out.println("School task added successfully!");
    }
    
    private void addPersonalTask() {
        System.out.println("Enter task title:");
        String title = scanner.nextLine();
        System.out.println("Enter priority:");
        String priority = scanner.nextLine();
        System.out.println("Enter task description:");
        String description = scanner.nextLine();
        System.out.println("Enter due date (YYYY-MM-DD):");
        LocalDate dueDate = LocalDate.parse(scanner.nextLine());
        System.out.println("Enter category:");
        String category = scanner.nextLine();
        System.out.println("Enter location:");
        String location = scanner.nextLine();
        PersonalTask personalTask = new PersonalTask(title, priority, description, dueDate, category, location);
        tasks.add(personalTask);
        System.out.println("Personal task added successfully!");
    }
    
    private void addHouseholdChore() {
        System.out.println("Enter task title:");
        String title = scanner.nextLine();
        System.out.println("Enter priority:");
        String priority = scanner.nextLine();
        System.out.println("Enter task description:");
        String description = scanner.nextLine();
        System.out.println("Enter due date (YYYY-MM-DD):");
        LocalDate dueDate = LocalDate.parse(scanner.nextLine());
        System.out.println("Enter room:");
        String room = scanner.nextLine();
        System.out.println("Enter equipment needed:");
        String equipmentNeeded = scanner.nextLine();
        HouseholdChores householdChore = new HouseholdChores(title, priority, description, dueDate, room, equipmentNeeded);
        tasks.add(householdChore);
        System.out.println("Household chore added successfully!");
    }
    
    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }
}

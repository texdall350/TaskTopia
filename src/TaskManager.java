import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private List<Task> tasks;
    private Scanner scanner;
    Table taskTable = new Table();
    TextColors TEXT = new TextColors();

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
        //Table taskTable = new Table();
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
        System.out.print("\n\tEnter task title: ");
        String title = scanner.nextLine();
        
        String priority = prioritySetLevel();
            
        System.out.print("\n\tEnter task description: ");
        String description = scanner.nextLine();
        LocalDate dueDate = parseDate();
        return new TaskDetails(title, priority, description, dueDate);
    }

    private void addBasicTask() {
        TaskDetails details = getCommonTaskDetails();
        Task basicTask = new Task(details.title, details.priority, details.description, details.dueDate);
        tasks.add(basicTask);
        System.out.println("Basic task added successfully! ");
    }

    private void addWorkTask() {
        TaskDetails details = getCommonTaskDetails();
        System.out.print("\n\tEnter project: ");
        String project = scanner.nextLine();
        WorkTask workTask = new WorkTask(details.title, details.priority, details.description, details.dueDate, project,
                parseTime());
        tasks.add(workTask);
        System.out.println("Work task added successfully!");
    }

    private void addSchoolTask() {
        TaskDetails details = getCommonTaskDetails();
        System.out.print("\n\tEnter subject: ");
        String subject = scanner.nextLine();
        System.out.print("\n\tEnter assignment type: ");
        String assignmentType = scanner.nextLine();
        SchoolTask schoolTask = new SchoolTask(details.title, details.priority, details.description, details.dueDate,
                subject, assignmentType);
        tasks.add(schoolTask);
        System.out.println("School task added successfully!");
    }

    private void addPersonalTask() {
        TaskDetails details = getCommonTaskDetails();
        System.out.print("\n\tEnter category: ");
        String category = scanner.nextLine();
        System.out.print("\n\tEnter location: ");
        String location = scanner.nextLine();
        PersonalTask personalTask = new PersonalTask(details.title, details.priority, details.description,
                details.dueDate, category, location);
        tasks.add(personalTask);
        System.out.println("Personal task added successfully!");
    }

    private void addHouseholdChore() {
        TaskDetails details = getCommonTaskDetails();
        System.out.print("\n\tEnter room: ");
        String room = scanner.nextLine();
        System.out.print("\n\tEnter equipment needed: ");
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
            System.out.print("\n\tEnter deadline time (h:mm AM/PM): ");
            String timeString = scanner.nextLine();

            try {
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
                time = LocalTime.parse(timeString, timeFormatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println(TEXT.RED + "***Invalid time format. Please use 'h:mm AM/PM' format.***" + TEXT.RESET);
            }
        }

        return time;
    }

    private LocalDate parseDate() {
        LocalDate date = null;

        while (true) {
            System.out.print("\n\tEnter due date (YYYY-MM-DD): ");
            String dateString = scanner.nextLine();

            try {
                date = LocalDate.parse(dateString);
                break;
            } catch (DateTimeParseException e) {
                System.out.println(TEXT.RED + "***Invalid time format. Please use 'YYYY-MM-DD' format.***" + TEXT.RESET);
            }
        }

        return date;
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (Task task : tasks) {
                System.out.println(task + "\n");
            }
        }
    }

    public void sortTasksByDueDate() {
        tasks.sort(Comparator.comparing(Task::getDueDate));
    }

    public void sortTasksByPriority() {
        Comparator<Task> priorityComparator = Comparator.comparingInt
            (obj -> obj.priority.equals(TEXT.RED + "High" + TEXT.RESET) ? 0 : obj.priority.equals(TEXT.YELLOW + "Normal" + TEXT.RESET) ? 1 : 2);
        Collections.sort(tasks, priorityComparator);
        //tasks.sort(Comparator.comparing(Task::getPriority));
    }

    public void sortTasksByType() {
        Comparator<Task> priorityComparator = Comparator.comparingInt
            (obj -> obj.getClass().getSimpleName().equals("Task") ? 0 : obj.getClass().getSimpleName().equals("WorkTask") ? 1 : 
                obj.getClass().getSimpleName().equals("SchoolTask") ? 2 : obj.getClass().getSimpleName().equals("PersonalTask") ? 3 : 4);
        Collections.sort(tasks, priorityComparator);
        //tasks.sort(Comparator.comparing(Task::getTaskType));
    }

    public void markTaskAsCompleted() {
        System.out.println("\nList of all your tasks:\n");
        for (Task task : tasks) {
            System.out.println("\t" + task.getTitle());   
        }
        System.out.print("\nEnter the title of the task to mark as completed from the list above: ");
        String title = scanner.nextLine();

        Task foundTask = findTaskByTitle(title);
        while(true){
            if (foundTask != null) {
                System.out.println(TEXT.GREEN + "Task marked as completed!" + TEXT.RESET);
                foundTask.setCompleted(true);
                break;
            } else {
                System.out.println(TEXT.RED + "Task not found." + TEXT.RESET);
                System.out.println("Please input valid task name: ");
                title = scanner.nextLine();
                foundTask = findTaskByTitle(title);
            }
        }
    }

    public void removeTask() {
        System.out.println("\nList of all your tasks:\n");
        for (Task task : tasks) {
            System.out.println("\t" + task.getTitle());
        }
        System.out.print("\nEnter the title of the task to remove from the list above: ");
        String title = scanner.nextLine();

        Task taskToRemove = findTaskByTitle(title);

        while(true){
            if (taskToRemove != null) {
                System.out.println(TEXT.GREEN + "Task removed successfully!" + TEXT.RESET);
                tasks.remove(taskToRemove); 
                break;
            } else {
                System.out.println(TEXT.RED + "Task not found." + TEXT.RESET);
                System.out.println("Please input valid task name: ");
                title = scanner.nextLine();
                taskToRemove = findTaskByTitle(title);
            }
        }
    }

    public void editTask(){
        System.out.println("\nList of all your tasks:\n");
        for (Task task : tasks) {
            System.out.println("\t" + task.getTitle());
        }
        System.out.print("\nEnter the title of the task to edit from the list above: ");
        String title = scanner.nextLine();

        Task taskToEdit = findTaskByTitle(title);

        while(true){
            if (taskToEdit != null) {
                //System.out.println(TEXT.GREEN + "Task removed successfully!" + TEXT.RESET);
                System.out.println("\n" + taskToEdit);
                System.out.print("\nPlease enter the variable you would like to edit: ");
                String variableToEdit = scanner.nextLine();

                boolean again = true; 

                while(again)
                    again = false;
                    switch(variableToEdit){
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
                            LocalDate newDueDate =  parseDate();
                            taskToEdit.setDueDate(newDueDate);
                            System.out.println(TEXT.GREEN + "Successfully edited due date!" + TEXT.RESET);
                        break;

                        case "Complete":
                            System.out.print("Is this task complete? [Y/N]: ");
                            String yesOrNo = scanner.nextLine();
                            Boolean newComplete;
                            if(yesOrNo.equalsIgnoreCase("Y")){
                                newComplete = true;
                            }
                            else{
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
                            variableToEdit = scanner.nextLine();
                            again = true;
                        break;
                    }
                break;
            } else {
                System.out.println(TEXT.RED + "Task not found." + TEXT.RESET);
                System.out.println("Please input valid task name: ");
                title = scanner.nextLine();
                taskToEdit = findTaskByTitle(title);
            }
        }
    }

    private Task findTaskByTitle(String title) {
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                return task;
            }
        }
        return null;
    }

    public String prioritySetLevel(){
        System.out.print("\n\tEnter priority (" + TEXT.RED + "High," + TEXT.GREEN + " Low," + TEXT.YELLOW + " Normal" + TEXT.RESET + "): ");
        String priority = scanner.nextLine();

        while(true){
            if(priority.equalsIgnoreCase("High")){
                priority = TEXT.RED + "High" + TEXT.RESET ;
                break;
            }
            if(priority.equalsIgnoreCase("Low")){
                priority = TEXT.GREEN + "Low" + TEXT.RESET ;
                break;
            }
            if(priority.equalsIgnoreCase("Normal")){
                priority = TEXT.YELLOW + "Normal" + TEXT.RESET ;
                break;
            }
            System.out.println(TEXT.RED + "\tINVALID PRIORITY LEVEL!" + TEXT.RESET);
            System.out.print("\tEnter priority (" + TEXT.RED + "High," + TEXT.GREEN + " Low," + TEXT.YELLOW + " Normal" + TEXT.RESET + "): ");
            priority = scanner.nextLine();
        }
        return priority;
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

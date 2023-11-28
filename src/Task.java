import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// Base class for all types of tasks
public class Task {
    // Task attributes
    protected String title;
    protected String priority;
    protected String description;
    protected LocalDate dueDate;
    protected boolean isCompleted;

    TextColors TEXT = new TextColors();

    // Constructor for creating a new task with default completion status as false
    public Task(String title, String priority, String description, LocalDate dueDate) {
        this(title, priority, description, dueDate, false);
    }

    // Constructor for creating a new task with specified completion status
    public Task(String title, String priority, String description, LocalDate dueDate, boolean isCompleted) {
        this.title = title;
        this.priority = priority;
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
    }

    // Converts task details to a CSV format string
    public String toCSV() {
        return "Task," + title + "," + priority + "," + description + "," + dueDate + "," + isCompleted;
    }

    // Getters and setters for task attributes
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriority() {
        return this.priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isIsCompleted() {
        return this.isCompleted;
    }

    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    // Generates a string representation of the task
    @Override
    public String toString() {
        TaskManager taskManager = new TaskManager();
        // Formatting date and completion status for display
        String formattedDate = dueDate.format(DateTimeFormatter.ofPattern("dd MMM yy"));
        String completionStatus = isCompleted ? TEXT.GREEN + "Yes" + TEXT.RESET : TEXT.RED + "No" + TEXT.RESET;

        // Determining the type of task for display
        String classType = getClass().getSimpleName();
        if ("Task".equals(classType)) {
            classType = "Basic";
        } else {
            classType = classType.substring(0, classType.length() - 4);
        }

        return classType + " Task" +
                "\n\tTitle: " + title +
                "\n\tPriority: " + taskManager.priorityColorSet(priority) +
                "\n\tDescription: " + description +
                "\n\tDue Date: " + formattedDate +
                "\n\tComplete: " + completionStatus;
    }
}

// WorkTask class extends Task, adding specific attributes for work-related
// tasks
class WorkTask extends Task {
    // Attributes specific to a work task
    private String project; // Name of the project associated with the task
    private LocalTime deadlineTime; // Time of the day by which the task needs to be completed

    // Constructor for creating a new work task without specifying completion status
    public WorkTask(String title, String priority, String description, LocalDate dueDate, String project,
            LocalTime deadlineTime) {
        super(title, priority, description, dueDate);
        this.project = project;
        this.deadlineTime = deadlineTime;
    }

    // Constructor for creating a new work task with specified completion status
    public WorkTask(String title, String priority, String description, LocalDate dueDate, boolean isCompleted,
            String project, LocalTime deadlineTime) {
        super(title, priority, description, dueDate, isCompleted);
        this.project = project;
        this.deadlineTime = deadlineTime;
    }

    // Getters and setters for project and deadlineTime

    public String getProject() {
        return this.project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public LocalTime getDeadlineTime() {
        return this.deadlineTime;
    }

    public void setDeadlineTime(LocalTime deadlineTime) {
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String toCSV() {
        // Converts work task details to a CSV format string
        return "WorkTask," + title + "," + priority + "," + description + "," + dueDate + "," + isCompleted + ","
                + project + "," + deadlineTime;
    }

    // Override toString method to include work task specific details
    @Override
    public String toString() {
        String formattedTime = deadlineTime.format(DateTimeFormatter.ofPattern("h:mm a"));
        return super.toString() +
                "\n\tProject: " + project +
                "\n\tDeadline Time: " + formattedTime;
    }
}

// SchoolTask class extends Task, adding specific attributes for school-related
// tasks
class SchoolTask extends Task {
    // Attributes specific to a school task
    private String subject; // Subject associated with the task
    private String assignmentType; // Type of the assignment (e.g., essay, project)

    // Constructor for creating a new school task without specifying completion
    // status
    public SchoolTask(String title, String priority, String description, LocalDate dueDate, String subject,
            String assignmentType) {
        super(title, priority, description, dueDate);
        this.subject = subject;
        this.assignmentType = assignmentType;
    }

    // Constructor for creating a new school task with specified completion status
    public SchoolTask(String title, String priority, String description, LocalDate dueDate, boolean isCompleted,
            String subject, String assignmentType) {
        super(title, priority, description, dueDate, isCompleted);
        this.subject = subject;
        this.assignmentType = assignmentType;
    }

    // Getters and setters for subject and assignmentType

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAssignmentType() {
        return this.assignmentType;
    }

    public void setAssignmentType(String assignmentType) {
        this.assignmentType = assignmentType;
    }

    @Override
    public String toCSV() {
        // Converts school task details to a CSV format string
        return "SchoolTask," + title + "," + priority + "," + description + "," + dueDate + "," + isCompleted + ","
                + subject + "," + assignmentType;
    }

    // Override toString method to include school task specific details
    @Override
    public String toString() {
        return super.toString() +
                "\n\tSubject: " + subject +
                "\n\tAssignment: " + assignmentType;
    }
}

// PersonalTask class extends Task, adding specific attributes for personal
// tasks
class PersonalTask extends Task {
    private String category; // Category of the personal task (e.g., hobby, errand)
    private String location; // Location where the task will be performed

    // Constructor for creating a new personal task without specifying completion
    // status
    public PersonalTask(String title, String priority, String description, LocalDate dueDate, String category,
            String location) {
        super(title, priority, description, dueDate);
        this.category = category;
        this.location = location;
    }

    // Constructor for creating a new personal task with specified completion status
    public PersonalTask(String title, String priority, String description, LocalDate dueDate, boolean isCompleted,
            String category, String location) {
        super(title, priority, description, dueDate, isCompleted);
        this.category = category;
        this.location = location;
    }

    // Converts personal task details to a CSV format string
    @Override
    public String toCSV() {
        return "PersonalTask," + title + "," + priority + "," + description + "," + dueDate + "," + isCompleted + ","
                + category + "," + location;
    }

    // Getters and setters for category and location

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Override toString method to include personal task specific details
    @Override
    public String toString() {
        return super.toString() +
                "\n\tCategory: " + category +
                "\n\tLocation: " + location;
    }
}

// HouseholdChores class extends Task, adding specific attributes for household
// chores
class HouseholdChores extends Task {
    private String room; // Room where the chore needs to be done
    private String equipmentNeeded; // Equipment needed to complete the chore

    // Constructor for creating a new household chore without specifying completion
    // status
    public HouseholdChores(String title, String priority, String description, LocalDate dueDate, String room,
            String equipmentNeeded) {
        super(title, priority, description, dueDate);
        this.room = room;
        this.equipmentNeeded = equipmentNeeded;
    }

    // Constructor for creating a new household chore with specified completion
    // status
    public HouseholdChores(String title, String priority, String description, LocalDate dueDate, boolean isCompleted,
            String room, String equipmentNeeded) {
        super(title, priority, description, dueDate, isCompleted);
        this.room = room;
        this.equipmentNeeded = equipmentNeeded;
    }

    // Converts household chore details to a CSV format string
    @Override
    public String toCSV() {
        return "HouseholdChores," + title + "," + priority + "," + description + "," + dueDate + "," + isCompleted + ","
                + room + "," + equipmentNeeded;
    }

    // Getters and setters for room and equipmentNeeded

    public String getRoom() {
        return this.room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getEquipmentNeeded() {
        return this.equipmentNeeded;
    }

    public void setEquipmentNeeded(String equipmentNeeded) {
        this.equipmentNeeded = equipmentNeeded;
    }

    // Override toString method to include household chore specific details
    @Override
    public String toString() {
        return super.toString() +
                "\n\tRoom: " + room +
                "\n\tEquipment Needed: " + equipmentNeeded;
    }
}

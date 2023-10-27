import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String title;
    protected String priority;

    protected String description;
    protected LocalDate dueDate;
    protected boolean isCompleted;



    public Task(String title, String priority, String description, LocalDate dueDate) {
        this(title, priority, description, dueDate, true);
    }

    public Task(String title, String priority, String description, LocalDate dueDate, boolean isCompleted) {
        this.title = title;
        this.priority = priority;
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
    }
    
    

    public String toCSV() {
        return "Task," + title + "," + priority + "," + description + "," + dueDate + "," + isCompleted;
    }

    public String getPriority() {
        return this.priority;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public boolean isIsCompleted() {
        return this.isCompleted;
    }

    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) +
                ", isCompleted=" + isCompleted +
                '}';
    }
    

}

class WorkTask extends Task {
    private String project;
    private String deadlineTime;


    public WorkTask(String title, String priority, String description, LocalDate dueDate,String project, String deadlineTime) {
        super(title, priority, description, dueDate);
        this.project = project;
        this.deadlineTime = deadlineTime;
    }

    public WorkTask(String title, String priority, String description, LocalDate dueDate, boolean isCompleted ,String project, String deadlineTime) {
        super(title, priority, description, dueDate, isCompleted);
        this.project = project;
        this.deadlineTime = deadlineTime;
    }
    

    // Getters and setters for project and deadlineTime

    @Override
    public String toCSV() {
        return "WorkTask," + title + "," + priority + "," + description + "," + dueDate + "," + isCompleted + "," + project + "," + deadlineTime;
    }


    public String getProject() {
        return this.project;
    }

    public String getDeadlineTime() {
        return this.deadlineTime;
    }

    @Override
    public String toString() {
        return super.toString() + ", Project='" + project + '\'' + ", Deadline Time='" + deadlineTime + '\'' + '}';
    }
}

class SchoolTask extends Task {
    private String subject;
    private String assignmentType;

    public SchoolTask(String title, String priority, String description, LocalDate dueDate, String subject, String assignmentType) {
        super(title, priority, description, dueDate);
        this.subject = subject;
        this.assignmentType = assignmentType;
    }

    public SchoolTask(String title, String priority, String description, LocalDate dueDate,boolean isCompleted, String subject, String assignmentType) {
        super(title, priority, description, dueDate, isCompleted);
        this.subject = subject;
        this.assignmentType = assignmentType;
    }

    @Override
    public String toCSV() {
        return "SchoolTask," + title + "," + priority + "," + description + "," + dueDate + "," + isCompleted + "," + subject + "," + assignmentType;
    }


    // Getters and setters for subject and assignmentType

    public String getSubject() {
        return this.subject;
    }

    public String getAssignmentType() {
        return this.assignmentType;
    }

    @Override
    public String toString() {
        return super.toString() + ", Subject='" + subject + '\'' + ", Assignment Type='" + assignmentType + '\'' + '}';
    }
}

class PersonalTask extends Task {
    private String category;
    private String location;


    public PersonalTask(String title, String priority, String description, LocalDate dueDate, String category, String location) {
        super(title, priority, description, dueDate);
        this.category = category;
        this.location = location;
    }

    public PersonalTask(String title, String priority, String description, LocalDate dueDate,boolean isCompleted, String category, String location) {
        super(title, priority, description, dueDate, isCompleted);
        this.category = category;
        this.location = location;
    }
    

    @Override
    public String toCSV() {
        return "PersonalTask," + title + "," + priority + "," + description + "," + dueDate + "," + isCompleted + "," + category + "," + location;
    }


    // Getters and setters for category and priorityLevel

    public String getCategory() {
        return this.category;
    }

    public String getLocation() {
        return this.location;
    }


    @Override
    public String toString() {
        return super.toString() + ", Category='" + category + '\'' + ", Priority Level='"  + '\'' + '}';
    }
}

class HouseholdChores extends Task {
    private String room;
    private String equipmentNeeded;

    public HouseholdChores(String title, String priority, String description, LocalDate dueDate, String room, String equipmentNeeded) {
        super(title, priority, description, dueDate);
        this.room = room;
        this.equipmentNeeded = equipmentNeeded;
    }

    public HouseholdChores(String title, String priority, String description, LocalDate dueDate,boolean isCompleted, String room, String equipmentNeeded) {
        super(title, priority, description, dueDate, isCompleted);
        this.room = room;
        this.equipmentNeeded = equipmentNeeded;
    }

    @Override
    public String toCSV() {
        return "HouseholdChores," + title + "," + priority + "," + description + "," + dueDate + "," + isCompleted + "," + room + "," + equipmentNeeded;
    }


    // Getters and setters for room and equipmentNeeded

    public String getRoom() {
        return this.room;
    }

    public String getEquipmentNeeded() {
        return this.equipmentNeeded;
    }

    @Override
    public String toString() {
        return super.toString() + ", Room='" + room + '\'' + ", Equipment Needed='" + equipmentNeeded + '\'' + '}';
    }
}

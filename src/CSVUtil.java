import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

// The CSVUtil class provides utility methods to load and save tasks to and from a CSV file.
public class CSVUtil {

    // Loads tasks from a CSV file and returns them as a List of Task objects.
    public static List<Task> loadTasks(String filename) {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Task task;
                boolean isCompleted = Boolean.parseBoolean(values[5]);
                // Switch statement to determine the type of task and create the appropriate
                // object.
                switch (values[0]) {
                    case "WorkTask":
                        // Creating a WorkTask object from CSV data.
                        task = new WorkTask(values[1], values[2], values[3], LocalDate.parse(values[4]), values[6],
                                LocalTime.parse(values[7]));
                        break;
                    case "SchoolTask":
                        // Creating a SchoolTask object from CSV data.
                        task = new SchoolTask(values[1], values[2], values[3], LocalDate.parse(values[4]), values[6],
                                values[7]);
                        break;
                    case "PersonalTask":
                        // Creating a PersonalTask object from CSV data.
                        task = new PersonalTask(values[1], values[2], values[3], LocalDate.parse(values[4]), values[6],
                                values[7]);
                        break;
                    case "HouseholdChores":
                        // Creating a HouseholdChores object from CSV data.
                        task = new HouseholdChores(values[1], values[2], values[3], LocalDate.parse(values[4]),
                                values[6], values[7]);
                        break;
                    default:
                        // Default case for creating a generic Task object.
                        task = new Task(values[1], values[2], values[3], LocalDate.parse(values[4]));
                        break;
                }
                task.setCompleted(isCompleted);
                tasks.add(task);
            }
        } catch (IOException e) {
            // Handling IOException and printing an error message.
            System.out.println("Error reading from CSV file: " + e.getMessage());
        }
        return tasks;
    }

    // Saves a list of tasks to a CSV file.
    public static void saveTasks(List<Task> tasks, String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Task task : tasks) {
                // Writing each task to the CSV file in CSV format.
                bw.write(task.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            // Handling IOException and printing an error message.
            System.out.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}

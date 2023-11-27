import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {

    public static List<Task> loadTasks(String filename) {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Task task;
                boolean isCompleted = Boolean.parseBoolean(values[5]);
                switch (values[0]) {
                    case "WorkTask":
                        task = new WorkTask(values[1], values[2], values[3], LocalDate.parse(values[4]), values[6],
                                LocalTime.parse(values[7]));
                        break;
                    case "SchoolTask":
                        task = new SchoolTask(values[1], values[2], values[3], LocalDate.parse(values[4]), values[6],
                                values[7]);
                        break;
                    case "PersonalTask":
                        task = new PersonalTask(values[1], values[2], values[3], LocalDate.parse(values[4]), values[6],
                                values[7]);
                        break;
                    case "HouseholdChores":
                        task = new HouseholdChores(values[1], values[2], values[3], LocalDate.parse(values[4]),
                                values[6], values[7]);
                        break;
                    default:
                        task = new Task(values[1], values[2], values[3], LocalDate.parse(values[4]));
                        break;
                }
                task.setCompleted(isCompleted);
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error reading from CSV file: " + e.getMessage());
        }
        return tasks;
    }

    public static void saveTasks(List<Task> tasks, String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Task task : tasks) {
                bw.write(task.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}

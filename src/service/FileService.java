package service;
import model.*;

import javafx.collections.ObservableList;

import java.io.*;
import java.util.Scanner;

public class FileService {

    //========================
    // FILE SETTINGS
    //========================

    private static final String FILE_NAME =
            "tasks.txt";

    private static final String SEPARATOR = ";;";

    //========================
    // SAVE TASKS
    //========================

    public void saveTasks( ObservableList<Task> taskList)
    {
        try (BufferedWriter writer = new BufferedWriter
                (new FileWriter(FILE_NAME)))
        {
            for(Task task : taskList)
            {
                writer.write(
                        task.getTitle() + SEPARATOR
                        + task.getDescription() + SEPARATOR
                        + task.getPriority() + SEPARATOR
                        + task.getCategory() + SEPARATOR
                        +task.getDueDate() + SEPARATOR
                        + task.isCompleted()
                );

                writer.newLine();
            }
        }
        catch (IOException e)
        {
            System.out.println("Error saving tasks.");
            e.printStackTrace();
        }
    }

    //========================
    // LOAD TASKS
    //========================

    public void loadTasks(ObservableList<Task> taskList)
    {
        File file = new File(FILE_NAME);

        if(!file.exists()) return;

        try(Scanner scanner = new Scanner(file))
        {
            while(scanner.hasNextLine()) {

                String line = scanner.nextLine();

                String[] parts = line.split(SEPARATOR);

                // Skip corrupted rows
                if (parts.length != 6) {continue; }

                String title = parts[0].trim();
                String description = parts[1].trim();
                String priority = parts[2].trim();
                String category = parts[3].trim();
                String date = parts[4].trim();

                boolean completed = Boolean.parseBoolean(parts[5].trim());

                    Task task = new Task(title,
                            description,
                            priority,
                            category,
                            date);

                task.setCompleted(completed);

                taskList.add(task);
            }
        }
        catch(IOException e)
        {
            System.out.println("Error loading tasks.");
            e.printStackTrace();
        }
    }
}

package service;
import model.Task;

import javafx.collections.ObservableList;
import javafx.scene.control.*;

import java.time.LocalDate;

public class TaskService {

    //=========================
    // ADD TASK
    //=========================

    public void addTask(ObservableList<Task> taskList,
                        TextField titleField,
                        TextArea descriptionField,
                        ComboBox<String> priorityBox,
                        ComboBox<String> categoryBox,
                        DatePicker datePicker)
    {
        String title = titleField.getText().trim();
        String description = descriptionField.getText().trim();
        String priority = priorityBox.getValue();
        String category = categoryBox.getValue();
        LocalDate dueDate = datePicker.getValue();

        // Prevent save-file corruption
        title = title.replace(";;", "");
        description = description.replace(";;", "");

        // Validate title
        if(title.isEmpty())
        {
            showAlert(
                    "Error",
                    "Task title cannot be empty!"
            );
            return;
        }

        //Validate date
        if(dueDate.isBefore(LocalDate.now()))
        {
            showAlert(
                    "Invalid Date",
                    "Due date cannot be earlier than today."
            );
            return;
        }

        //Create Task
        Task task = new Task(
                title,
                description,
                priority,
                category,
                dueDate.toString()
        );

        taskList.add(task);

        clearFields(
                titleField,
                descriptionField,
                priorityBox,
                categoryBox,
                datePicker
        );
    }

    //=========================
    // DELETE TASK
    //=========================

    public void deleteTask(ObservableList<Task> taskList,
                           Task selectedTask)
    {
        if(selectedTask == null)
        {
            showAlert(
                    "Error",
                    "Please select a task."
            );

            return;
        }

        taskList.remove(selectedTask);
    }

    //=========================
    // TOGGLE COMPLETED
    //=========================

    public void toggleTaskCompletion(Task task)
    {
        if(task == null)
        {
            showAlert(
                    "Error",
                    "Please select a task."
            );
            return;
        }

        task.setCompleted(!task.isCompleted());
    }

    //=========================
    // LOAD TASK INTO FORM
    //=========================

    public void loadTaskForEditing( Task task,
                                    TextField titleField,
                                    TextArea descriptionField,
                                    ComboBox<String> priorityBox,
                                    ComboBox<String> categoryBox,
                                    DatePicker datePicker)
    {
        if(task == null)
        {
            showAlert(
                    "Error",
                    "Please select a task edit."
            );
            return;
        }

        titleField.setText(task.getTitle());
        descriptionField.setText(task.getDescription());
        priorityBox.setValue(task.getPriority());
        categoryBox.setValue(task.getCategory());

        try{
            datePicker.setValue(LocalDate.parse(task.getDueDate()));
        }
        catch(Exception e)
        {
            datePicker.setValue(LocalDate.now());
        }
    }

    //=========================
    // CLEAR ALL TASKS
    //=========================

    public void clearAllTasks(ObservableList<Task> taskList)
    {
        taskList.clear();
    }

    //=========================
    // COUNT COMPLETED TASKS
    //=========================

    public int getCompletedCount(ObservableList<Task> taskList)
    {
        int completed = 0;

        for (Task task : taskList) {
            if (task.isCompleted()) {
                completed++;
            }
        }
        return completed;
    }

    //=========================
    // RESET FORM
    //=========================

    private void clearFields(
            TextField titleField,
            TextArea descriptionField,
            ComboBox<String> priorityBox,
            ComboBox<String> categoryBox,
            DatePicker datePicker
    )
    {
        titleField.clear();
        descriptionField.clear();
        priorityBox.setValue("Medium");
        categoryBox.setValue("Study");
        datePicker.setValue(LocalDate.now());
    }

    //=========================
    // ALERT HELPER
    //=========================

    private void showAlert(String title, String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

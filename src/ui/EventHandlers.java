package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import language.LanguageManager;

import model.Task;

import service.FileService;
import service.TaskService;

public class EventHandlers {

    //=========================
    // DEPENDENCIES
    //=========================

    private final UIComponents uiComponents;

    private final TaskService taskService = new TaskService();

    private final FileService fileService = new FileService();

    private final LanguageManager languageManager = new LanguageManager();

    // =========================
    // CONSTRUCTOR
    // =========================

    public EventHandlers(UIComponents uiComponents) {
        this.uiComponents = uiComponents;
    }

    // =========================
    // INITIALIZE EVENTS
    // =========================

    public void initialize() {

        //Load saved tasks
        fileService.loadTasks(uiComponents.taskList);

        updateStatus();

        // =========================
        // LANGUAGE SWITCH
        // =========================

        uiComponents.languageButton.setOnAction(e -> {
            languageManager.switchLanguage(uiComponents);
            updateStatus();
        });

        // =========================
        // ADD TASK
        // =========================

        uiComponents.addButton.setOnAction(e -> {

            taskService.addTask(
                    uiComponents.taskList,
                    uiComponents.titleField,
                    uiComponents.descriptionField,
                    uiComponents.priorityBox,
                    uiComponents.categoryBox,
                    uiComponents.datePicker
            );

            fileService.saveTasks(uiComponents.taskList);
            updateStatus();
        });

        // =========================
        // DELETE TASK
        // =========================

        uiComponents.deleteButton.setOnAction(e -> {

            Task selectedTask = uiComponents.taskListView.getSelectionModel().getSelectedItem();

            taskService.deleteTask(uiComponents.taskList,selectedTask);
            fileService.saveTasks(uiComponents.taskList);
            updateStatus();
        });

        // =========================
        // COMPLETE TASK
        // =========================

        uiComponents.completeButton.setOnAction(e -> {
            Task selectedTask = uiComponents.taskListView.
                    getSelectionModel().getSelectedItem();

            taskService.toggleTaskCompletion(selectedTask);
            uiComponents.taskListView.refresh();

            fileService.saveTasks(uiComponents.taskList);
            updateStatus();
        });

        // =========================
        // EDIT TASK
        // =========================

        uiComponents.editButton.setOnAction(e -> {
            Task selectedTask = uiComponents.taskListView.
                    getSelectionModel().getSelectedItem();

            taskService.loadTaskForEditing(
                    selectedTask,
                    uiComponents.titleField,
                    uiComponents.descriptionField,
                    uiComponents.priorityBox,
                    uiComponents.categoryBox,
                    uiComponents.datePicker
            );

            if(selectedTask != null)
            {
                uiComponents.taskList.remove(selectedTask);

                fileService.saveTasks(uiComponents.taskList);

                updateStatus();
            }
        });

        // =========================
        // CLEAR ALL TASKS
        // =========================

        uiComponents.clearButton.setOnAction(e -> {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("Clear Tasks");
            alert.setHeaderText(null);
            alert.setContentText("Delete All Tasks?");

            if(alert.showAndWait().get() == ButtonType.OK)
            {
                taskService.clearAllTasks(uiComponents.taskList);
                fileService.saveTasks(uiComponents.taskList);
                updateStatus();
            }
        });

        // =========================
        // SEARCH BUTTON
        // =========================

        uiComponents.searchButton.setOnAction(e -> {
            String searchText = uiComponents.searchField.
                    getText().trim().toLowerCase();

            if(searchText.isEmpty())
            {
                uiComponents.taskListView.setItems(uiComponents.taskList);
                return;
            }

            ObservableList<Task> filteredTasks = FXCollections.observableArrayList();

            for(Task task : uiComponents.taskList)
            {
                String searchableText =
                        task.getTitle() + " "
                        + task.getDescription() + " "
                        + task.getPriority() + " "
                        + task.getCategory() + " "
                        + task.getDueDate();

                if(searchableText.toLowerCase().contains(searchText))
                {
                    filteredTasks.add(task);
                }
            }

            uiComponents.taskListView.setItems(filteredTasks);

            if(filteredTasks.isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Search");
                alert.setHeaderText(null);
                alert.setContentText("No matching tasks found.");
                alert.showAndWait();
            }
        });

        // =========================
        // SEARCH ON ENTER KEY
        // =========================

        uiComponents.searchField.setOnAction(e ->
            uiComponents.searchButton.fire()
        );

        // =========================
        // RESTORE TASK LIST
        // =========================

        uiComponents.searchField.textProperty()
                .addListener((
                        observable,
                        oldValue,
                        newValue) -> {

                    if(newValue.trim().isEmpty())
                    {
                        uiComponents.taskListView.setItems(uiComponents.taskList);
                    }
                });
    }

    // =========================
    // UPDATE STATUS LABEL
    // =========================

    private void updateStatus()
    {
        int total = uiComponents.taskList.size();

        int completed = taskService.getCompletedCount(uiComponents.taskList);

        int pending = total-completed;

        if(languageManager.isGerman())
        {
            uiComponents.statusLabel.setText(
                    "Aufgaben: " + total
                            + " | Erledigt: " + completed
                            + " | Offen: " +
                            pending);
        }
        else
        {
            uiComponents.statusLabel.setText(
                    "Tasks: " + total
                            + " | Completed: " + completed
                            + " | Pending: " +
                            pending);
        }

    }
}

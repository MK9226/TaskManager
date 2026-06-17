package ui;
import model.Task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.time.LocalDate;

public class UIComponents {

    // =========================
    // INPUTS
    // ========================

    public TextField titleField =  new TextField();
    public TextArea descriptionField  = new TextArea();
    public ComboBox<String> priorityBox  =  new ComboBox<>();
    public ComboBox<String> categoryBox =  new ComboBox<>();
    public DatePicker datePicker = new DatePicker(LocalDate.now());
    public TextField searchField =  new TextField();

    // =========================
    // BUTTONS
    // =========================

    public Button newTaskButton = new Button("+ New Task");

    public Button addButton = new Button("Add");
    public Button editButton = new Button("Edit");
    public Button deleteButton = new Button("Delete");
    public Button completeButton = new Button("Complete");
    public Button clearButton = new Button("Clear All");
    public Button searchButton = new Button("\uD83D\uDD0D");
    public Button languageButton = new Button("Deutsch");

    // =========================
    // LABELS
    // =========================

    public Label titleLabel = new Label("Task Manager");
    public Label statusLabel = new Label();

    // =========================
    // TASK DATA
    // =========================

    public ObservableList<Task> taskList =
            FXCollections.observableArrayList();
    public ListView<Task> taskListView = new ListView<>();

    // =========================
    // EXTRA CONTAINERS
    // =========================

    public VBox formBox;
    public HBox actionBox;
    public HBox searchBox;

    // =========================
    // MAIN LAYOUT
    // =========================

    private BorderPane layout;

    // =========================
    // CONSTRUCTOR
    // =========================

    public UIComponents() {
        setupComponents();
        createLayout();
    }

    // =========================
    // COMPONENT SETUP
    // =========================

    private void setupComponents(){

        // Title Style

        titleLabel.getStyleClass().add("title-label");

        // Text Inputs

        titleField.setPromptText("Task Title");

        descriptionField.setPromptText("Task Description");

        descriptionField.setPrefRowCount(4);

        searchField.setPromptText("Search Tasks");
        searchField.setPrefWidth(350);

        //Priority

        priorityBox.getItems().addAll
                ("High", "Medium", "Low","Urgent");

        priorityBox.setValue("Medium");

        //Category

        categoryBox.getItems().addAll
                ("Study", "Work", "Finance","Health","Personal");

        categoryBox.setValue("Study");

        //Button Sizes

        newTaskButton.setPrefWidth(140);
        newTaskButton.setPrefHeight(40);

        addButton.setPrefWidth(120);
        editButton.setPrefWidth(120);
        deleteButton.setPrefWidth(120);
        completeButton.setPrefWidth(120);
        clearButton.setPrefWidth(120);

        searchButton.setPrefWidth(50);
        searchButton.setPrefHeight(35);

        languageButton.setPrefHeight(40);

        // CSS IDs

        searchButton.setId("searchButton");
        languageButton.setId("languageButton");
        deleteButton.setId("deleteButton");
        clearButton.setId("clearButton");
        statusLabel.setId("statusLabel");

        // Task List

        taskListView.setItems(taskList);
        taskListView.setPrefHeight(400);

    }

    // =========================
    // CREATE LAYOUT
    // =========================

    private void createLayout(){

        layout = new BorderPane();

        // Header

        BorderPane header  = new BorderPane();

        HBox leftHeader = new HBox(
                15,
                titleField,
                newTaskButton);

        leftHeader.setAlignment(Pos.CENTER_LEFT);

        header.setLeft(leftHeader);
        header.setRight(languageButton);

        // Search

          searchBox = new HBox(
                10,
                searchField,
                searchButton
        );

        searchBox.setAlignment(Pos.CENTER_LEFT);

        // Form

        formBox = new VBox(
                10,
                titleField,
                descriptionField,
                priorityBox,
                categoryBox,
                datePicker,
                addButton);

        formBox.setVisible(false);
        formBox.setManaged(false);

        // Action Buttons

        actionBox = new HBox(
                10,
                editButton,
                deleteButton,
                completeButton,
                clearButton
        );

        actionBox.setAlignment(Pos.CENTER_LEFT);

        // Top Section

        VBox topSection = new VBox(
                15,
                header,
                searchBox,
                formBox,
                actionBox
        );

        topSection.setPadding(new Insets(20));

        // Center Section

        VBox centerSection = new VBox(taskListView);

        centerSection.setPadding(new Insets(0,
                20,
                20,
                20));

        // Bottom Section

        HBox bottomSection = new HBox(statusLabel);

        bottomSection.setPadding(new Insets(0,
                20,
                20,
                20));

        // Root

        layout.setTop(topSection);
        layout.setCenter(centerSection);
        layout.setBottom(bottomSection);

    }

    // =========================
    // GET LAYOUT
    // =========================

    public BorderPane getLayout(){
        return layout;
    }

}

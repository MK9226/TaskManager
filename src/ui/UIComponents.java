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
    // =========================

    public TextField titleField =  new TextField();
    public TextArea descriptionField  = new TextArea();
    public ComboBox<String> priorityBox  =  new ComboBox<>();
    public ComboBox<String> categoryBox =  new ComboBox<>();
    public DatePicker datePicker = new DatePicker(LocalDate.now());
    public TextField searchField =  new TextField();

    // =========================
    // BUTTONS
    // =========================

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
    // MAIN LAYOUT
    // =========================

    private VBox layout;

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
        searchField.setPrefWidth(250);

        //Priority

        priorityBox.getItems().addAll
                ("High", "Medium", "Low","Urgent");

        priorityBox.setValue("Medium");

        //Category

        categoryBox.getItems().addAll
                ("Study", "Work", "Finance","Health","Personal");

        categoryBox.setValue("Study");

        //Button Sizes

        addButton.setPrefWidth(120);
        editButton.setPrefWidth(120);
        deleteButton.setPrefWidth(120);
        completeButton.setPrefWidth(120);
        clearButton.setPrefWidth(120);

        searchButton.setPrefWidth(50);
        searchButton.setPrefHeight(35);

        languageButton.setPrefHeight(60);

        // CSS IDs

        searchButton.setId("searchButton");
        languageButton.setId("languageButton");
        deleteButton.setId("deleteButton");
        clearButton.setId("clearButton");
        statusLabel.setId("statusLabel");

        // Task List

        taskListView.setItems(taskList);
        taskListView.setPrefHeight(350);

    }

    // =========================
    // CREATE LAYOUT
    // =========================

    private void createLayout(){

        // Header

        BorderPane header  = new BorderPane();

        header.setLeft(titleLabel);
        header.setRight(languageButton);

        // Controls

        HBox topControls = new HBox(
                10,
                priorityBox,
                categoryBox,
                datePicker,
                searchField
        );

        topControls.setAlignment(Pos.CENTER);

        // Button

        HBox buttonLayout = new HBox(
                10,
                addButton,
                editButton,
                deleteButton,
                completeButton,
                clearButton
        );

        buttonLayout.setAlignment(Pos.CENTER);

        // Search

        HBox searchBox = new HBox(
                10,
                searchField,
                searchButton
        );

        searchBox.setAlignment(Pos.CENTER_LEFT);

        // Main Layout

        layout = new VBox(15);

        layout.setPadding(new Insets(20));

        layout.getChildren().addAll(
                header,
                titleField,
                descriptionField,
                topControls,
                buttonLayout,
                searchBox,
                taskListView,
                statusLabel
        );
    }

    // =========================
    // GET LAYOUT
    // =========================

    public VBox getLayout(){
        return layout;
    }

}

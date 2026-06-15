package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import ui.EventHandlers;
import ui.UIComponents;

public class Main extends Application {

    //============================
    // WINDOWS SETTINGS
    //============================

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;


    //============================
    // APPLICATION START
    //============================

    @Override
    public void start(Stage primaryStage) {

        // Create UI
        UIComponents uiComponents = new UIComponents();

        // Connect Events
        EventHandlers handlers = new EventHandlers(uiComponents);
        handlers.initialize();

        // Create Scene
        Scene scene = new Scene(
                uiComponents.getLayout(),
                1000,
                700
        );

        // Load CSS
        scene.getStylesheets().add("file:resources/style.css");

        primaryStage.setTitle("Task Manager");

        primaryStage.setScene(scene);

        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(550);

        primaryStage.show();
    }

    //============================
    // MAIN METHOD
    //============================

    public static void main(String[] args) {
        launch(args);
    }

}

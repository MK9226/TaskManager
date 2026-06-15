package language;

import ui.UIComponents;

public class LanguageManager {

    private boolean german = false;

    public boolean isGerman()
    {
        return german;
    }

    public void switchLanguage(UIComponents ui){

        german = !german;

        if(german)
        {
            ui.titleLabel.setText("Aufgabenmanager");

            ui.titleField.setPromptText("Aufgabentitel");
            ui.descriptionField.setPromptText("Aufgabenbeschreibung");

            ui.addButton.setText("Hinzufügen");
            ui.editButton.setText("Bearbeiten");
            ui.deleteButton.setText("Löschen");
            ui.completeButton.setText("Erledigt");
            ui.clearButton.setText("Alle Löschen");

            ui.languageButton.setText("English");

            ui.priorityBox.getItems().setAll(
                    "Niedrig",
                    "Mittel",
                    "Hoch",
                    "Dringend"
            );

            ui.priorityBox.setValue("Mittel");

            ui.categoryBox.getItems().setAll(
                    "Lernen",
                    "Arbeit",
                    "Finanzen",
                    "Gesundheit",
                    "Persönlich"
            );

            ui.categoryBox.setValue("Lernen");

            ui.searchField.setPromptText("Aufgaben suchen");
        }
        else
        {
            ui.titleLabel.setText("Tax Manager");

            ui.titleField.setPromptText("Tax Title");
            ui.descriptionField.setPromptText("Tax Description");

            ui.addButton.setText("Add");
            ui.editButton.setText("Edit");
            ui.deleteButton.setText("Delete");
            ui.completeButton.setText("Complete");
            ui.clearButton.setText("Clear All");

            ui.languageButton.setText("Deutsch");

            ui.priorityBox.getItems().setAll(
                    "Low",
                    "Medium",
                    "High",
                    "Urgent"
            );

            ui.priorityBox.setValue("Medium");

            ui.categoryBox.getItems().setAll(
                    "Study",
                    "Work",
                    "Finance",
                    "Health",
                    "Personal"
            );

            ui.categoryBox.setValue("Study");

            ui.searchField.setPromptText("Search Tasks");
        }
    }
}

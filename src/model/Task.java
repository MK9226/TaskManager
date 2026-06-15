package model;

public class Task {

    //=========================
    // FIELDS
    //=========================

    private String title;
    private String description;
    private String priority;
    private String category;
    private String dueDate;

    private boolean completed;

    //=========================
    // DEFAULT CONSTRUCTOR
    //=========================

    public Task()
    {
        this.title = "";
        this.description = "";

        this.priority = "Medium";
        this.category = "Study";

        this.dueDate = "N/A";

        this.completed = false;
    }

    //=========================
    // MAIN CONSTRUCTOR
    //=========================

    public Task(
            String title,
            String description,
            String priority,
            String category,
            String dueDate
    )
    {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.category = category;
        this.dueDate = dueDate;

        this.completed = false;
    }

    //=========================
    // TITLE
    //=========================

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    //=========================
    // DESCRIPTION
    //=========================

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    //=========================
    // PRIORITY
    //=========================

    public String getPriority()
    {
        return priority;
    }

    public void setPriority(String priority)
    {
        this.priority = priority;
    }

    //=========================
    // CATEGORY
    //=========================

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    //=========================
    // DUE DATE
    //=========================

    public String getDueDate()
    {
        return dueDate;
    }

    public void setDueDate(String dueDate)
    {
        this.dueDate = dueDate;
    }

    //=========================
    // COMPLETED
    //=========================

    public boolean isCompleted()
    {
        return completed;
    }

    public void setCompleted(boolean completed)
    {
        this.completed = completed;
    }

    //=========================
    // DISPLAY IN LISTVIEW
    //=========================

    @Override
    public String toString()
    {
        String status = completed ? "✓" : "";
        return title
                + "|" + priority
                + "|" + category
                + "|" + dueDate
                + " "
                + status;
    }
}

package com.example.todolistapp.model;

import java.io.Serializable;

public class EventModel implements Serializable {
    private int id;

    private String eventContent;

    private String categoryName;

    public static final String STATUS_TO_DO = "To do";
    public static final String STATUS_DOING = "Doing";
    public static final String STATUS_DONE = "Done";

    private String status = STATUS_TO_DO;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    private int categoryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEventContent(String eventContent) {
        this.eventContent = eventContent;
    }

    public String getEventContent() {
        return eventContent;
    }

}

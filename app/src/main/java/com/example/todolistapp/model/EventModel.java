package com.example.todolistapp.model;

import java.io.Serializable;

public class EventModel implements Serializable {
    private int id = 1;

    private String summaryTitle;

    private String detailContent;

    private String eventContent;

    public static final String STATUS_TO_DO = "To do";
    public static final String STATUS_DOING = "Doing";
    public static final String STATUS_DONE = "Done";

    private String status = STATUS_TO_DO;

    public EventModel() {
        id += 1;
    }

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

    public String getSummaryTitle() {
        return summaryTitle;
    }

    public void setSummaryTitle(String summaryTitle) {
        this.summaryTitle = summaryTitle;
    }

    public void setEventlContent(String eventContent) {
        this.eventContent = eventContent;
    }

    public String getEventContent() {
        return eventContent;
    }

}

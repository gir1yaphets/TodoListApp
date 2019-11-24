package com.example.todolistapp.model;

import java.io.Serializable;

public class EventModel implements Serializable {
    private String eventContent;

    public static final String STATUS_TO_DO = "To do";
    public static final String STATUS_DOING = "Doing";
    public static final String STATUS_DONE = "Done";

    private String status = STATUS_TO_DO;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public void setEventlContent(String eventContent) {
        this.eventContent = eventContent;
    }

    public String getEventContent() {
        return eventContent;
    }

}

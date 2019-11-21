package com.example.todolistapp.model;

import java.io.Serializable;

public class EventModel implements Serializable {
    private String summaryTitle;

    private String detailContent;

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



    public String getSummaryTitle() {
        return summaryTitle;
    }

    public void setSummaryTitle(String summaryTitle) {
        this.summaryTitle = summaryTitle;
    }

    public String getDetailContent() {
        return detailContent;
    }

    public void setDetailContent(String detailContent) {
        this.detailContent = detailContent;
    }


}

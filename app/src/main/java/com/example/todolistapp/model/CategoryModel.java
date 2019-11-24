package com.example.todolistapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class CategoryModel implements Serializable {
    private int id;

    private String category;

    private ArrayList<EventModel> eventList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<EventModel> getEventList() {
        return eventList;
    }

    public void setEventList(ArrayList<EventModel> eventList) {
        this.eventList = eventList;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void addEvent(EventModel event) {
        eventList.add(event);
    }

    public void deleteEvent(int id) {
        if (id < eventList.size()) {
            eventList.remove(id);
        }
    }
}

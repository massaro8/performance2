package com.example.performance2;

public class ToDoModel {
    private int is, status;
    private String task;
    private String titles;

    public ToDoModel() {
    }

    public ToDoModel(String titles) {
        this.titles = titles;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }
}





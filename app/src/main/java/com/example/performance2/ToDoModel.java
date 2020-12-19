package com.example.performance2;

public class ToDoModel {
    private int is, status;
    private String task;

    public ToDoModel() {
    }

    public ToDoModel(String task, int is, int status) {
        this.task = task;
        this.is = is;
        this.status = status;
    }

    public int getIs() {
        return is;
    }

    public void setIs(int is) {
        this.is = is;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}





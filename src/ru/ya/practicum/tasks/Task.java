package ru.ya.practicum.tasks;

import ru.ya.practicum.status.Status;

public class Task {
    private int id;
    private String name;
    private Status status;
    private String description;
    private TaskType taskType;

    public Task(int id, String name, Status status, String description) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.description = description;
    }

    public Task(String name, String description, TaskType taskType, Status status) {
        this.name = name;
        this.description = description;
        this.taskType = taskType;
        this.status = status;
    }

    public Task(String name, String description, TaskType taskType) {
        this.name = name;
        this.description = description;
        this.taskType = taskType;
    }

    public Task(String name, String description, int id, TaskType taskType) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.taskType = taskType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Задача {" +
                "id = " + id +
                ", Название = '" + name + '\'' +
                ", Описание = '" + description + '\'' +
                ", Статус = " + status +
                '}';
    }
}


package ru.yandex.praktikum.tasks;

import ru.yandex.praktikum.status.Status;

public class Task {
    private int id;
    private String name;
    private Status status;
    private String description;


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


    public Task(int id, String name, Status status, String description) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.description = description;

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


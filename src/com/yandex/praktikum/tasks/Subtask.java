package com.yandex.praktikum.tasks;

import com.yandex.praktikum.status.Status;

public class Subtask extends Task {
    private int idEpic;

    public String toString() {
        return " SubTask id = " + getId() +
                ", Название = " + getName() +
                ", Описание = " + getDescription() +
                ", Статус = " + getStatus() +
                ", Относится к эпику = " + getIdEpic();
    }

    public int getIdEpic() {
        return idEpic;
    }

    public void setIdEpic(int idEpic) {
        this.idEpic = idEpic;
    }

    public Subtask(int id, String name, Status status, String description, int idEpic) {
        super(id, name, status, description);
        this.idEpic = idEpic;
    }

}

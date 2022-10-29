package ru.ya.practicum.tasks;

import ru.ya.practicum.status.Status;

public class Subtask extends Task {
    private int idEpic;

    public Subtask(int id, String name, Status status, String description, int idEpic) {
        super(id, name, status, description);
        this.idEpic = idEpic;
    }

    public Subtask(String name, String description, int idEpic, TaskType taskType) {
        super(name, description, idEpic, taskType);

    }

    public int getIdEpic() {
        return idEpic;
    }

    public void setIdEpic(int idEpic) {
        this.idEpic = idEpic;
    }

    public String toString() {
        return " SubTask id = " + getId() +
                ", Название = " + getName() +
                ", Описание = " + getDescription() +
                ", Статус = " + getStatus() +
                ", Относится к эпику = " + getIdEpic();
    }

}

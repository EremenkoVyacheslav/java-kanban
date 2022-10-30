package ru.ya.practicum.tasks;

import ru.ya.practicum.status.Status;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

    private List<Integer> epicsSubtask;

    public Epic(int id, String name, String description) {
        super(id, name, Status.NEW, description);
        epicsSubtask = new ArrayList<>();
    }

    public Epic(String name, String description, TaskType taskType) {
        super(name, description, taskType);
        epicsSubtask = new ArrayList<>();
    }

    public List<Integer> getEpicsSubtask() {
        return epicsSubtask;
    }

    public void setEpicsSubtask(List<Integer> epicsSubtask) {
        this.epicsSubtask = epicsSubtask;
    }

    @Override
    public String toString() {
        return " Эпик {" +
                " Название = '" + getName() + '\'' +
                ", Описание= '" + getDescription() + '\'' +
                ", Статус= '" + getStatus() + '\'' +
                '}';
    }
}

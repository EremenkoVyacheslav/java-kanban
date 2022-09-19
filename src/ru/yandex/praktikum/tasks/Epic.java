package ru.yandex.praktikum.tasks;

import ru.yandex.praktikum.status.Status;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

    private List<Integer> epicsSubtask = new ArrayList<>();

    public Epic(int id, String name, String description) {
        super(id, name, Status.NEW, description);
    }

    public List<Integer> getEpicsSubtask() {
        return epicsSubtask;
    }

    public void setEpicsSubtask(List<Integer> epicsSubtask) {
        this.epicsSubtask = epicsSubtask;
    }

    @Override
    public String toString() {
        return "Эпик {" +
                "Название = '" + getName() + '\'' +
                ", Описание= '" + getDescription() + '\'' +
                ", Статус= '" + getStatus() + '\'' +
                '}';
    }
}
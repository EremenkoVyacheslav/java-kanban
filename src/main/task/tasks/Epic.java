package main.task.tasks;

import main.task.status.Status;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

    private List<Integer> epicsSubtask;

    private LocalDateTime endTime;

    public Epic(int id, String name, String description, Status status, TaskType taskType) {
        super(id, name, description, status, TaskType.EPIC, LocalDateTime.now(), Duration.ofMinutes(1));
        epicsSubtask = new ArrayList<>();
    }

    public Epic(String name, String description, Status status) {
        super(name, description, status);
        epicsSubtask = new ArrayList<>();
    }

    public List<Integer> getEpicsSubtask() {
        return epicsSubtask;
    }

    public void setEpicsSubtask(List<Integer> epicsSubtask) {
        this.epicsSubtask = epicsSubtask;
    }

    @Override
    public LocalDateTime getEndTime() {
        if(getDuration() != null && getStartTime() != null) {
            return getStartTime().minus(getDuration());
        } else {
            return null;
        }
    }

    @Override
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return  getId() + ", "
                + TaskType.EPIC +
                ", " + getName() + ' ' +
                ", " + getStatus() + ' ' +
                ", " + getDescription() + ' ' +
                ", " + getStartTime() + ' ' +
                ", " + getDuration() + ' ' +
                ", " + getEndTime();
    }
}

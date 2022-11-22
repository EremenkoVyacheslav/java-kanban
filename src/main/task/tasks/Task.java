package main.task.tasks;

import main.task.status.Status;

import java.time.Duration;
import java.time.LocalDateTime;

public class Task {
    private int id;
    private String name;
    private Status status;
    private String description;
    private TaskType taskType;
    private LocalDateTime startTime;
    private Duration duration;
    private LocalDateTime endTime;

    public Task(int id, String name, String description, Status status, TaskType taskType,
                                                                        LocalDateTime startTime, Duration duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.taskType = taskType;
        this.duration = duration;
        this.startTime = startTime;
    }

    public Task(String name, String description,  Status status, LocalDateTime startTime, Duration duration) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.startTime = startTime;
        this.duration = duration;
    }

    public Task(String name, String description, Status status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Task(String name, String description, int id, TaskType taskType) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.taskType = taskType;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
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
        return  getId() + ", "
                + TaskType.TASK +
                ", " + getName() + ' ' +
                ", " + getStatus() + ' ' +
                ", " + getDescription() + ' ' +
                ", " + getStartTime() + ' ' +
                ", " + getDuration() + ' ' +
                ", " + getEndTime();
    }
}


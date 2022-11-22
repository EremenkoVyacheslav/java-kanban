package main.task.tasks;

import main.task.status.Status;

import java.time.Duration;
import java.time.LocalDateTime;

public class Subtask extends Task {
    private int idEpic;

    public Subtask(int id, String taskName, Status status, String description, TaskType taskType, LocalDateTime startTime, Duration duration, int idEpic) {
        super(id, taskName, description, status, taskType, startTime, duration);
        this.idEpic = idEpic;
    }

    public Subtask(String name, String description, int idEpic, Status status) {
        super(name, description, status);
        this.idEpic = idEpic;

    }

    public int getIdEpic() {
        return idEpic;
    }

    public void setIdEpic(int idEpic) {
        this.idEpic = idEpic;
    }

    public String toString() {
        return  getId() +
               ", " + TaskType.SUBTASK + ' ' +
                ", " + getName() + ' ' +
                ", " + Status.IN_PROGRESS + ' ' +
                ", " + getDescription() + ' ' +
                ", " + idEpic + ' ' +
                ", " + getStartTime() + ' ' +
                ", " + getDuration() + ' ' +
                ", " + getEndTime();
    }

}

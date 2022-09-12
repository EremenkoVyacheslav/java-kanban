package tasks;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
private final List<Integer> epicsSubtask = new ArrayList<>();

    public List<Integer> getEpicsSubtask() {
        return epicsSubtask;
    }


    public Epic(int id, String name, String description) {
        super(id, name, Status.NEW, description);
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

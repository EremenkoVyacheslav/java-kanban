package ru.ya.practicum.manager;
import ru.ya.practicum.tasks.Task;

import java.util.List;

public interface HistoryManager {
    void add(Task task);
    void remove(int id);
    List<Task> getHistory();

}

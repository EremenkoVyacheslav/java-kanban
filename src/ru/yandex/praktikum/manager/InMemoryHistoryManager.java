package ru.yandex.praktikum.manager;

import ru.yandex.praktikum.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private static final int MAX_COUNT = 10;
    private final List<Task> historyTasks = new ArrayList<>();

    @Override
    public void add(Task task) {
        if (historyTasks.size() < MAX_COUNT) {
            historyTasks.add(task);
        } else {
            System.out.println("Невозможно добавить задачу");
        }
    }

    @Override
    public List<Task> getHistory() {
        return historyTasks;
    }
}

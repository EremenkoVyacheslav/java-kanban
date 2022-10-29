package ru.ya.practicum.utils;

import ru.ya.practicum.manager.HistoryManager;
import ru.ya.practicum.manager.InMemoryHistoryManager;
import ru.ya.practicum.manager.InMemoryTaskManager;
import ru.ya.practicum.manager.TaskManager;

public class Managers {

    public static TaskManager getInMemoryTaskManager(HistoryManager historyManager) {
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}

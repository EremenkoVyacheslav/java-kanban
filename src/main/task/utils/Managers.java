package main.task.utils;

import main.manager.HistoryManager;
import main.manager.InMemoryHistoryManager;
import main.manager.InMemoryTaskManager;
import main.manager.TaskManager;

public class Managers {

    public static TaskManager getInMemoryTaskManager(HistoryManager historyManager) {
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}

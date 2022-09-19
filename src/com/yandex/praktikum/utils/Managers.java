package com.yandex.praktikum.utils;

import com.yandex.praktikum.manager.HistoryManager;
import com.yandex.praktikum.manager.InMemoryHistoryManager;
import com.yandex.praktikum.manager.InMemoryTaskManager;
import com.yandex.praktikum.manager.TaskManager;

public class Managers {

    public static TaskManager getInMemoryTaskManager(HistoryManager historyManager) {
        return new InMemoryTaskManager(historyManager);
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}

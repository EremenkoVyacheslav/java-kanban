package com.yandex.praktikum.manager;

import com.yandex.praktikum.tasks.Task;

import java.util.List;

public interface HistoryManager {

    void add(Task task);

    List<Task> getHistory();
}
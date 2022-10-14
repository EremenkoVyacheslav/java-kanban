package ru.yandex.praktikum.manager;

import ru.yandex.praktikum.tasks.Epic;
import ru.yandex.praktikum.tasks.Subtask;
import ru.yandex.praktikum.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {

    Task createTasks(Task task);

    Epic createEpics(Epic epic);

    Subtask createSubtasks(Subtask subtask);

    ArrayList<Task> getAllTasks();

    ArrayList<Subtask> getAllSubtask();

    ArrayList<Epic> getAllEpic();

    void deleteTask(int id);

    void deleteSubtask(int id);

    void deleteEpic(int id);

    void deleteAllTasks();

    void deleteAllSubtasks();

    void deleteAllEpicTasks();

    void updateStatus(Epic epic);

    ArrayList<Subtask> getById(int number);

    void updateTask(Task task);

    void updateSubtask(Subtask subtask);

    void updateEpic(Epic epic);

    List<Task> getHistory();

    Task getTask(int id);

    Subtask getSubtask(int id);

    Epic getEpic(int id);

    void remove(int id);
}

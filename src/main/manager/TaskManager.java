package main.manager;

import org.jetbrains.annotations.NotNull;
import main.task.tasks.Subtask;
import main.task.tasks.Task;
import main.task.tasks.Epic;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {

    @NotNull void createTasks(Task task);

    @NotNull void createEpics(Epic epic);

    void createSubtasks(Subtask subtask);

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
    public void getTaskEndTime(Task task);
    public void getEpicEndTime(Epic epic);
    public void intersection();
    public void getSubtaskEndTime(Subtask subtask);
}

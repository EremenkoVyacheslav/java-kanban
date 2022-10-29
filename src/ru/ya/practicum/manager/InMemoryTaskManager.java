package ru.ya.practicum.manager;

import org.jetbrains.annotations.NotNull;
import ru.ya.practicum.tasks.Subtask;
import ru.ya.practicum.tasks.Task;
import ru.ya.practicum.status.Status;
import ru.ya.practicum.tasks.Epic;
import ru.ya.practicum.utils.Managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {
    private int id = 1;

    final HashMap<Integer, Task> tasks = new HashMap<>();
    final HashMap<Integer, Epic> epics = new HashMap<>();
    final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    final HistoryManager historyManager = Managers.getDefaultHistory();


    @Override
    @NotNull
    public Task createTasks(Task task) {
        task.setId(id++);
        tasks.put(task.getId(), task);

        return task;
    }

    @Override
    @NotNull
    public Epic createEpics(Epic epic) {

        epic.setId(id++);
        epics.put(epic.getId(), epic);

        return epic;
    }

    @Override
    @NotNull
    public Subtask createSubtasks(Subtask subtask) {
        subtask.setId(id++);
        subtasks.put(subtask.getId(), subtask);
        epics.get(subtask.getIdEpic()).getEpicsSubtask().add(subtask.getId());
        updateStatus(epics.get(subtask.getIdEpic()));
        return subtask;
    }

    @Override
    public ArrayList<Task> getAllTasks() {
        System.out.println(tasks);
        return new ArrayList<>(tasks.values());
    }

    @Override
    public ArrayList<Subtask> getAllSubtask() {
        System.out.println(subtasks);
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public ArrayList<Epic> getAllEpic() {
        System.out.println(epics);
        return new ArrayList<>(epics.values());
    }

    @Override
    public void deleteTask(int id) {
        tasks.remove(id);
    }

    public void deleteSubtask(int id) {
        epics.get(subtasks.get(id).getIdEpic()).getEpicsSubtask().remove((Integer) id);
        updateStatus(epics.get(subtasks.get(id).getIdEpic()));
        subtasks.remove(id);
    }

    @Override
    public void deleteEpic(int id) {
        for (var deleteEpics : epics.get(id).getEpicsSubtask()) {
            subtasks.remove(deleteEpics);
        }
        epics.remove(id);
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public void deleteAllSubtasks() {
        for (Map.Entry<Integer, Epic> epicTask : epics.entrySet()) {
            epicTask.getValue().getEpicsSubtask().clear();
            updateStatus(epicTask.getValue());
        }
        subtasks.clear();
    }

    @Override
    public void deleteAllEpicTasks() {
        epics.clear();
        subtasks.clear();
    }

    @Override @NotNull
    public void updateStatus(Epic epic) {

            int statusNew = 0;
            int statusDone = 0;
            for (var idSubtask : epic.getEpicsSubtask()) {
                Subtask subtask = subtasks.get(idSubtask);
                if ((subtask.getStatus() == Status.NEW)) {
                    statusNew++;
                } else if (subtask.getStatus() == Status.DONE) {
                    statusDone++;
                } else if (statusNew == epic.getEpicsSubtask().size() || epic.getEpicsSubtask().isEmpty()) {
                    epic.setStatus(Status.NEW);
                    epics.put(epic.getId(), epic);
                } else if (statusDone == epic.getEpicsSubtask().size()) {
                    epic.setStatus(Status.DONE);
                } else {
                    epic.setStatus(Status.IN_PROGRESS);
                }
                epics.put(epic.getId(), epic);

        }
    }

    @Override
    public ArrayList<Subtask> getById(int number) {
        System.out.println("Список подзадач " + number);
        ArrayList<Subtask> subtasksEpic = new ArrayList<>();
        for (var id : epics.get(number).getEpicsSubtask()) {
            System.out.println(subtasks.get(id).toString());
            subtasksEpic.add(subtasks.get(id));
        }
        return subtasksEpic;
    }

    @Override @NotNull
    public void updateTask(Task task) {
            tasks.put(task.getId(), task);
    }

    @Override @NotNull
    public void updateSubtask(Subtask subtask) {
            subtasks.put(subtask.getId(), subtask);
    }

    @Override @NotNull
    public void updateEpic(Epic epic) {
            updateStatus(epic);
            epics.put(epic.getId(), epic);
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    @Override
    public Task getTask(int id) {
        historyManager.add(tasks.get(id));
        return tasks.get(id);
    }

    @Override
    public Subtask getSubtask(int id) {
        historyManager.add(subtasks.get(id));
        return subtasks.get(id);
    }

    @Override
    public Epic getEpic(int id) {
        historyManager.add(epics.get(id));
        return epics.get(id);
    }

    @Override
    public void remove(int id) {
        historyManager.remove(id);
    }

    public void updateId(int id) {
        this.id = Math.max(this.id, id);
    }
}

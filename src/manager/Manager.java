package manager;

import tasks.Epic;
import tasks.Status;
import tasks.Subtask;
import tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Manager {
    private int id = 1;

    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();


    public Task createTasks(Task task) {
        task.setId(id++);
        tasks.put(task.getId(), task);
        return task;
    }


    public Epic createEpics(Epic epic) {
        epic.setId(id++);
        epics.put(epic.getId(), epic);
        return epic;
    }


    public Subtask createSubtasks(Subtask subtask) {
        subtask.setId(id++);
        subtasks.put(subtask.getId(), subtask);
        epics.get(subtask.getIdEpic()).getEpicsSubtask().add(subtask.getId());
        updateStatus(epics.get(subtask.getIdEpic()));
        return subtask;
    }

    public ArrayList<Task> getAllTasks() {
        System.out.println(tasks.toString());
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Subtask> getAllSubtask() {
        System.out.println(subtasks.toString());
        return new ArrayList<>(subtasks.values());
    }

    public ArrayList<Epic> getAllEpic() {
        System.out.println(epics.toString());
        return new ArrayList<>(epics.values());
    }

    public void deleteTask(int id) {
        tasks.remove(id);
    }

    public void deleteSubtask(int id) {
        epics.get(subtasks.get(id).getIdEpic()).getEpicsSubtask().remove((Integer) id);
        updateStatus(epics.get(subtasks.get(id).getIdEpic()));
        subtasks.remove(id);
    }

    public void deleteEpic(int id) {
        for (var deleteEpics : epics.get(id).getEpicsSubtask()) {
            subtasks.remove(deleteEpics);
        }
        epics.remove(id);
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    public void deleteAllSubtasks() {
        for (Map.Entry<Integer, Epic> epicTask : epics.entrySet()) {
            epicTask.getValue().getEpicsSubtask().clear();
            updateStatus(epicTask.getValue());
        }
        subtasks.clear();
    }

    public void deleteAllEpicTasks() {
        epics.clear();
        subtasks.clear();
    }

    private void updateStatus(Epic epic) {
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

    public ArrayList<Subtask> getById(int number) {
        System.out.println("Список подзадач " + number);
        ArrayList<Subtask> subtasksEpic = new ArrayList<>();
        for (var id : epics.get(number).getEpicsSubtask()) {
            System.out.println(subtasks.get(id).toString());
            subtasksEpic.add(subtasks.get(id));
        }
        return subtasksEpic;
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
    }

    public void updateEpic(Epic epic) {
        updateStatus(epic);
        epics.put(epic.getId(), epic);
    }
}

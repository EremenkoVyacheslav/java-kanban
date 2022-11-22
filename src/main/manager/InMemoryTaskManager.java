package main.manager;

import main.task.tasks.Subtask;
import main.task.tasks.Task;
import main.task.status.Status;
import main.task.tasks.Epic;
import main.task.utils.Managers;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class InMemoryTaskManager implements TaskManager {
    private int id = 1;

    final HashMap<Integer, Task> tasks = new HashMap<>();
    final HashMap<Integer, Epic> epics = new HashMap<>();
    final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    final HistoryManager historyManager = Managers.getDefaultHistory();


    protected Set<Task> getPrioritizedTasks = new TreeSet<>((o1, o2) -> {
        if (o1.getStartTime() == null && o2.getStartTime() == null) {
            return o1.getId() - o2.getId();
        }
        if (o1.getStartTime() == null) {
            return 1;
        }
        if (o2.getStartTime() == null) {
            return -1;
        }
        if (o1.getStartTime().isAfter(o2.getStartTime())) {
            return 1;
        }
        if (o1.getStartTime().isBefore(o2.getStartTime())) {
            return -1;
        }
        if (o1.getStartTime().isEqual(o2.getStartTime())) {
            return o1.getId() - o2.getId();
        }
        return 0;
    });

    @Override
    public void intersection() {
        LocalDateTime times = null;
        boolean timeExamination = true;
        for (Task task : getPrioritizedTasks) {
            if (timeExamination) {
                times = task.getEndTime();
                timeExamination = false;
            } else if (task.getStartTime() != null) {
                if (task.getStartTime().isBefore(times)) {
                    throw new ManagerSaveException("Упс, задачи пересеклись");
                }
                if (task.getStartTime().isEqual(times) || task.getStartTime().isAfter(times) ) {
                    times = task.getEndTime();
                }
            }
        }
    }

    @Override
    public void getTaskEndTime(Task task) {
        if (task.getStartTime() == null || task.getDuration() == null) return;
        LocalDateTime endTime = task.getStartTime().plus(task.getDuration());
        task.setEndTime(endTime);
    }

    @Override
    public void getSubtaskEndTime(Subtask subtask) {
        if (subtask.getDuration() == null || subtask.getStartTime() == null) return;
        LocalDateTime time = subtask.getStartTime().plus(subtask.getDuration());
        subtask.setEndTime(time);
        if (epics.containsKey(subtask.getIdEpic())) {
            getEpicEndTime(epics.get(subtask.getIdEpic()));
        }
    }

    @Override
    public void getEpicEndTime(Epic epic) {
        if(epic != null ) {
            if (epic.getEpicsSubtask().isEmpty()) {
                return;
            }
            LocalDateTime startTime;
            LocalDateTime endTime;
            startTime = LocalDateTime.MAX;
            endTime = LocalDateTime.MIN;
            epic.setStartTime(startTime);
            epic.setEndTime(endTime);
            for (Integer id : epic.getEpicsSubtask()) {
                if (subtasks.get(id).getStartTime().isBefore(startTime) && subtasks.get(id).getStartTime() != null) {
                    startTime = subtasks.get(id).getStartTime();
                }
                if (subtasks.get(id).getEndTime().isAfter(endTime) && subtasks.get(id).getStartTime() != null) {
                    endTime = subtasks.get(id).getEndTime();
                }
            }
            epic.setEndTime(endTime);
            epic.setStartTime(startTime);
            epic.setDuration(Duration.between(epic.getStartTime(), epic.getEndTime()));
        }
    }

    @Override
    public void createTasks(Task task) {
        if(task != null) {
            task.setId(id++);
            if (task.getDuration() == null) {
                task.setDuration(Duration.ZERO);
            }
            getPrioritizedTasks.add(task);
            getTaskEndTime(task);
            tasks.put(task.getId(), task);
        }

    }

    @Override
    public void createEpics(Epic epic) {
        if (epic != null) {
            epic.setId(id++);
            epics.put(epic.getId(), epic);
            getPrioritizedTasks.add(epic);
        }
    }

    @Override
    public void createSubtasks(Subtask subtask) {
        if (subtask != null) {
            subtask.setId(id++);
            if (subtask.getId() == -1) {
                subtask.setId(id);
                subtasks.put(id, subtask);
            } else {
                subtasks.put(subtask.getId(), subtask);
            }
            getPrioritizedTasks.add(subtask);
        }
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

    @Override
    public void updateStatus(Epic epic) {
        if (epic != null) {
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

    @Override
    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        if (subtask != null) {
            subtasks.put(subtask.getId(), subtask);
        }
    }

    @Override
    public void updateEpic(Epic epic) {
        if(epic != null ) {
            updateStatus(epic);
            epics.put(epic.getId(), epic);
        }
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

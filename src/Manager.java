import java.util.ArrayList;
import java.util.HashMap;

public class Manager {
    private int countId;
    private final ArrayList<Integer> epicId = new ArrayList<>();

    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();

    public void createTask(Object obj) {
        Task task = new Task();
        Subtask subtask = new Subtask();
        Epic epic;

        if (obj.getClass() == task.returnGetClass()) {
            countId++;
            task = (Task) obj;
            tasks.put(countId, task);

        } else if (obj.getClass() == subtask.returnGetClass()) {
            countId++;
            subtask = (Subtask) obj;

            if (epicId.contains(subtask.idEpic)) {
                subtasks.put(countId, subtask);
            } else {
                System.out.println("Такого эпика не существует!");
            }
        } else {
            countId++;
            epic = (Epic) obj;
            epicId.add(countId);
            epics.put(countId, epic);
        }
    }

    public void updateTask(Object obj, int id, String status) {
        if (tasks.containsKey(id)) {
            Task task = (Task) obj;
            task.status = status;
            tasks.put(id, task);

        } else if (subtasks.containsKey(id)) {
            Subtask subtask = (Subtask) obj;
            subtask.status = status;
            subtasks.put(id, subtask);
            if (status.equals("IN_PROGRESS")) {
                epics.get(subtask.idEpic).status = "IN_PROGRESS";
            } else if (status.equals("DONE")) {
                for (var taskId : subtasks.keySet()) {
                    Subtask subtask1 = subtasks.get(taskId);
                    if (subtask1.idEpic == id) {
                        if (!subtask.status.equals("DONE")) {
                            return;
                        }
                    }
                }
                epics.get(subtask.idEpic).status = "DONE";
            }

        } else if (epics.containsKey(id)) {
            Epic epic = (Epic) obj;
            tasks.put(id, epic);
        } else {
            System.out.println("Задача не найдена");
        }
    }

    public void printAll() {
        for (var id : tasks.keySet()) {
            System.out.println(id + " " + tasks.get(id));
        }
        for (var id : subtasks.keySet()) {
            System.out.println(id + " " + subtasks.get(id));
        }
        for (var id : epics.keySet()) {
            System.out.println(id + " " + epics.get(id));
        }
    }

    public void getById(int id) {
        if (tasks.containsKey(id)) {
            System.out.println("Задача " + tasks.get(id));
        } else if (subtasks.containsKey(id)) {
            System.out.println("Subtask " + subtasks.get(id));
        } else if (epics.containsKey(id)) {
            System.out.println("Эпик " + epics.get(id));
        } else {
            System.out.println("Задача не найдена");
        }
    }


    public void deleteById(int id) {
        if (tasks.containsKey(id)) {
            System.out.println("Задача с номером " + id + " удалена");
            tasks.remove(id);
        } else if (subtasks.containsKey(id)) {
            System.out.println("Subtask с номером " + id + " удален");
            subtasks.remove(id);
        } else if (epics.containsKey(id)) {
            System.out.println("Эпик с номером " + id + " удален");
            epics.remove(id);
            epicId.remove((Integer) id);
            ArrayList<Integer> listIdSubTask = new ArrayList<>();
            for (var taskId : subtasks.keySet()) {
                if (subtasks.get(taskId).idEpic == id) {
                    System.out.println("У эпика номер " + id + " также будет удален subtask " + taskId);
                    listIdSubTask.add(taskId);
                }
            }
            for (var count : listIdSubTask) {
                subtasks.remove(count);
            }

            subtasks.remove(id);

        } else {
            System.out.println("Задача не найдена");
        }
    }

    public void printEpicTask(int id) {
        if (epicId.contains(id)) {
            System.out.println("У эпика " + id + ":");
            for (var taskId : subtasks.keySet()) {
                if (subtasks.get(taskId).idEpic == id) {
                    System.out.println("subtask с номером " + id);
                }
            }
        } else {
            System.out.println("Такого эпика не существует");
        }
    }

    public void deleteAll() {
        tasks.clear();
        subtasks.clear();
        epics.clear();
        epicId.clear();
        System.out.println("Задачи удалены");
    }
}

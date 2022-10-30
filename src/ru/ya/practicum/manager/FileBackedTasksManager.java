package ru.ya.practicum.manager;


import org.jetbrains.annotations.NotNull;
import ru.ya.practicum.status.Status;
import ru.ya.practicum.tasks.Epic;
import ru.ya.practicum.tasks.Subtask;
import ru.ya.practicum.tasks.Task;
import ru.ya.practicum.tasks.TaskType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileBackedTasksManager extends InMemoryTaskManager {
    private static File file;
    private final String fileName;
    private static String[] lines;
    private static int id;
    private static TaskType taskType;
    private static String name;
    private static Status status;
    private static String description;

    public FileBackedTasksManager(File file) {

        FileBackedTasksManager.file = file;
        fileName = "Vse_sohranyaetsya_tyt";
    }

    public static void main(String[] args) {

        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(new File(String.valueOf(file)));
        Task task = new Task("Купить бублик", "Очень большой бублик", TaskType.TASK, Status.NEW);
        fileBackedTasksManager.createTasks(task);
        Task task1 = new Task("Съездить на рыбалку", "Поймать рыбу", TaskType.TASK, Status.IN_PROGRESS);
        fileBackedTasksManager.createTasks(task1);
        Epic epic = new Epic("Тут уже кончились идеи ", "Очень важное описание", TaskType.EPIC);
        fileBackedTasksManager.createEpics(epic);
        Subtask subtask = new Subtask("Господи, помоги это решить", "Очень надо", epic.getId(), TaskType.SUBTASK);
        fileBackedTasksManager.createSubtasks(subtask);
        Subtask subtask1 = new Subtask("Скоро Новый Год", "Нужно купить подарки", epic.getId(), TaskType.SUBTASK);
        fileBackedTasksManager.createSubtasks(subtask1);



        System.out.println(fileBackedTasksManager.getAllTasks());
        System.out.println(fileBackedTasksManager.getAllEpic());
        System.out.println(fileBackedTasksManager.getAllSubtask());


    }


    public static List<Integer> historyFromString(String value) {

        List<Integer> result = new ArrayList<>();
        String[] lines = value.split(",");
        for (String line : lines) {
            result.add(Integer.parseInt(line));
        }
        return result;
    }

    public static Task taskFromString(String value) {

        lines = value.split(",");
        id = Integer.parseInt(lines[0]);
        taskType = typeFromString(lines[1]);
        name = lines[2];
        status = statusFromString(lines[3]);
        description = lines[4];

        Task task = new Task(name, description, taskType, status);
        task.setId(id);
        task.setStatus(status);
        return task;
    }

    public static Epic epicFromString(String value) {

        lines = value.split(",");
        id = Integer.parseInt(lines[0]);
        taskType = typeFromString(lines[1]);
        name = lines[2];
        status = statusFromString(lines[3]);
        description = lines[4];

        Epic epic = new Epic(name, description, taskType);
        epic.setId(id);
        epic.setStatus(status);
        return epic;
    }


    public static Subtask subtaskFromString(String value) {

        lines = value.split(",");
        id = Integer.parseInt(lines[0]);
        taskType = typeFromString(lines[1]);
        name = lines[2];
        status = statusFromString(lines[3]);
        description = lines[4];
        int epic = Integer.parseInt(lines[5]);

        Subtask subtask = new Subtask(name, description, epic, taskType);
        subtask.setId(id);
        subtask.setStatus(status);
        return subtask;
    }


    static String toString(@NotNull HistoryManager historyManager) {

        List<Task> tasks = historyManager.getHistory();
        List<String> ids = new ArrayList<>();
        for (Task task : tasks) {
            int id = task.getId();
            ids.add(String.valueOf(id));
        }
        return String.join(",", ids);
    }

    public static TaskType typeFromString(String string) {

        return switch (string) {
            case "TASK" -> TaskType.TASK;
            case "EPIC" -> TaskType.EPIC;
            case "SUBTASK" -> TaskType.SUBTASK;
            default -> null;
        };
    }

    public static Status statusFromString(String string) {

        return switch (string) {
            case "NEW" -> Status.NEW;
            case "IN_PROGRESS" -> Status.IN_PROGRESS;
            case "DONE" -> Status.DONE;
            default -> null;
        };
    }

    public static FileBackedTasksManager loadFromFile(File file) {

        FileBackedTasksManager tasksManager = new FileBackedTasksManager(file);

        try {

            String readFile = Files.readString(file.toPath());
            String[] lines = readFile.split(System.lineSeparator());
            for (int i = 1; i < lines.length; i++) {
                String line = lines[i];
                if (line.isEmpty()) {
                    line = lines[i + 1];
                    break;
                }

                String[] type = line.split(",");
                String typeTask = type[3];

                switch (typeTask) {
                    case "TASK" -> {
                        Task task = taskFromString(lines[i]);
                        tasksManager.tasks.put(task.getId(), task);
                        tasksManager.updateId(task.getId());
                    }
                    case "EPIC" -> {
                        Epic epic = epicFromString(lines[i]);
                        tasksManager.epics.put(epic.getId(), epic);
                        tasksManager.updateId(epic.getId());
                    }
                    case "SUBTASK" -> {
                        Subtask subtask = subtaskFromString(lines[i]);
                        tasksManager.subtasks.put(subtask.getId(), subtask);
                        tasksManager.updateId(subtask.getId());
                    }
                }
            }
        } catch (IOException e) {
            throw new ManagerSaveException("Что-то пошло не так..");
        }
        return tasksManager;
    }

    private void save() {

        try {

            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write("id,type,name,status,description,epic \n");
            for (Task task : tasks.values()) {
                fileWriter.write(task.toString() + System.lineSeparator());
            }
            for (Task epic : epics.values()) {
                fileWriter.write(epic.toString() + System.lineSeparator());
            }
            for (Task subtask : subtasks.values()) {
                fileWriter.write(subtask.toString() + System.lineSeparator());
            }
            fileWriter.write(System.lineSeparator());
            fileWriter.write(toString(this.historyManager));
            fileWriter.close();
        } catch (IOException e) {
            throw new ManagerSaveException("Что-то пошло не так..");
        }
    }

    @Override
    public @NotNull Task createTasks(@NotNull Task task) {
        task = super.createTasks(task);
        save();
        return task;
    }

    @Override
    public @NotNull Epic createEpics(@NotNull Epic epic) {
        epic = super.createEpics(epic);
        save();
        return epic;
    }

    @Override

    public @NotNull Subtask createSubtasks(@NotNull Subtask subtask) {
        subtask = super.createSubtasks(subtask);
        save();
        return subtask;
    }

    @Override
    public void deleteTask(int id) {
        super.deleteTask(id);
        save();
    }

    @Override
    public void deleteSubtask(int id) {
        super.deleteSubtask(id);
        save();
    }

    @Override
    public void deleteEpic(int id) {
        super.deleteEpic(id);
        save();
    }

    @Override
    public void deleteAllTasks() {
        super.deleteAllTasks();
        save();
    }

    @Override
    public void deleteAllSubtasks() {
        super.deleteAllSubtasks();
        save();
    }

    @Override
    public void deleteAllEpicTasks() {
        super.deleteAllEpicTasks();
        save();
    }

    @Override
    public void updateStatus(@NotNull Epic epic) {
        super.updateStatus(epic);
        save();
    }

    @Override
    public void updateTask(@NotNull Task task) {
        super.updateTask(task);
        save();
    }

    @Override
    public void updateSubtask(@NotNull Subtask subtask) {
        super.updateSubtask(subtask);
        save();
    }

    @Override
    public void updateEpic(@NotNull Epic epic) {
        super.updateEpic(epic);
        save();
    }

    @Override
    public Task getTask(int id) {
        Task task = super.getTask(id);
        save();
        return task;
    }

    @Override
    public Subtask getSubtask(int id) {
        Subtask subtask = super.getSubtask(id);
        save();
        return subtask;
    }

    @Override
    public Epic getEpic(int id) {
        Epic epic = super.getEpic(id);
        save();
        return epic;
    }

    @Override
    public void remove(int id) {
        super.remove(id);
        save();
    }
}
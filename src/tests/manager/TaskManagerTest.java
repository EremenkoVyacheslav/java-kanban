package tests.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.manager.TaskManager;
import main.task.status.Status;
import main.task.tasks.Epic;
import main.task.tasks.Subtask;
import main.task.tasks.Task;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

abstract class TaskManagerTest<T extends TaskManager> {

    protected T taskManager;
    private Task task;
    private Epic epic;
    private Subtask subtask;


    @BeforeEach
    void beforeEach() {

        task = new Task("Тасочка", "Описание тасочки", Status.NEW);
        epic = new Epic("Эпик", "Эпическое описание", Status.IN_PROGRESS);
        subtask = new Subtask("Сабтаска, но это не точно", "Описание Сабтаски", 2 ,Status.NEW);


    }

    @Test
    void createTasks() {
        task = new Task("Тасочка", "Описание тасочки", Status.NEW);
        taskManager.createTasks(task);
        final Task savedTask = taskManager.getTask(1);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        final List<Task> tasks = taskManager.getAllTasks();

        assertNotNull(tasks, "Задачи на возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(0), "Задачи не совпадают.");
    }


    @Test
    void createEpics() {
        epic = new Epic("Эпик", "Эпическое описание", Status.IN_PROGRESS);
        taskManager.createEpics(epic);
        final Epic savedEpics = taskManager.getEpic(epic.getId());

        assertNotNull(savedEpics, "Задача не найдена.");
        assertEquals(epic, savedEpics, "Задачи не совпадают.");

        final List<Epic> epics = taskManager.getAllEpic();

        assertNotNull(epics, "Задачи на возвращаются.");
        assertEquals(1, epics.size(), "Неверное количество задач.");
        assertEquals(epic, epics.get(0), "Задачи не совпадают.");
    }

    @Test
    void createSubtasks() {
        epic = new Epic("Эпик", "Эпическое описание", Status.IN_PROGRESS);
        taskManager.createEpics(epic);
        subtask = new Subtask("Сабтаска, но это не точно", "Описание Сабтаски", 2 ,Status.NEW);

        taskManager.createSubtasks(subtask);
        final Subtask SavedSubTask = taskManager.getSubtask(subtask.getId());

        assertNotNull(SavedSubTask, "Подзадача не найдена.");

        final List<Subtask> subtasks = taskManager.getAllSubtask();

        assertNotNull(subtasks, "Подзадачи на возвращаются.");
        assertEquals(1, subtasks.size(), "Неверное количество подзадач.");
        assertEquals(subtask, subtasks.get(0), "Подзадачи не совпадают.");

    }

    @Test
    void removeTask() {
        task = new Task("Тасочка", "Описание тасочки", Status.NEW);
        taskManager.createTasks(task);
        taskManager.deleteTask(task.getId());
        assertTrue(taskManager.getAllTasks().isEmpty(), "Задача не удалена");
    }

    @Test
    void removeEpic() {
        epic = new Epic("Эпик", "Эпическое описание", Status.IN_PROGRESS);
        taskManager.createEpics(epic);
        taskManager.deleteEpic(epic.getId());
        assertTrue(taskManager.getAllTasks().isEmpty(), "Эпик не удалена");
    }

    @Test
    void removeSubtask() {
        subtask = new Subtask("Сабтаска, но это не точно", "Описание Сабтаски", 2 ,Status.NEW);
        taskManager.createSubtasks(subtask);
        taskManager.remove(subtask.getId());
        assertTrue(taskManager.getAllTasks().isEmpty(), "Сабтаска не удалена");
    }

    @Test
    void updateTask() {
        task = new Task("Тасочка", "Описание тасочки", Status.NEW);
        taskManager.createTasks(task);
        task.setName("Голубая Луна");
        taskManager.updateTask(task);
        assertEquals(task.getName(), "Голубая Луна", "Название задачи не обновилось");
    }

    @Test
    void updateEpic() {
        epic = new Epic("Эпик", "Эпическое описание", Status.IN_PROGRESS);
        taskManager.createTasks(epic);
        epic.setName("Голубая Луна");
        taskManager.updateEpic(epic);
        assertEquals(epic.getName(), "Голубая Луна", "Название задачи не обновилось");
    }

    @Test
    void updateSubtask() {
        subtask = new Subtask("Сабтаска, но это не точно", "Описание Сабтаски", 2 ,Status.NEW);
        taskManager.createSubtasks(subtask);
        subtask.setName("Голубая Луна");
        taskManager.updateSubtask(subtask);
        assertEquals(subtask.getName(), "Голубая Луна", "Название задачи не обновилось");
    }
}







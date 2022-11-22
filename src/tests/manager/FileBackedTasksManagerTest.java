package tests.manager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.manager.FileBackedTasksManager;
import main.task.status.Status;
import main.task.tasks.Epic;
import main.task.tasks.Task;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileBackedTasksManagerTest extends TaskManagerTest<FileBackedTasksManager> {
    private File file;
    String path = "Vse_sohranyaetsya_tyt";


    @BeforeEach
    void beforeEach() {
        file = new File(path);
        taskManager = new FileBackedTasksManager(file);
    }

    @AfterEach
    void afterEach() {
        assertTrue(file.delete());
    }

    @Test
    void EmptyListTasks() {
        Epic epic1 = new Epic("Эпик", "Эпическое описание", Status.IN_PROGRESS);
        taskManager.createEpics(epic1);
        FileBackedTasksManager loader = FileBackedTasksManager.loadFromFile(file);
        assertEquals(0, loader.getAllEpic().size());
        assertEquals(List.of(), loader.getAllSubtask());
    }

    @Test
    void EpicWithoutSubtasks() {

        Task task1 = new Task("Эпик", "Эпическое описание", Status.NEW);
        Task task2 = new Task("Эпик", "Эпическое описание", Status.NEW);
        taskManager.createTasks(task1);
        taskManager.createTasks(task2);
        assertEquals(0, taskManager.getHistory().size());
        taskManager.deleteAllTasks();
        FileBackedTasksManager loadFromFile = FileBackedTasksManager.loadFromFile(file);
        assertEquals(0, loadFromFile.getHistory().size());
    }

    @Test
    public void EmptyHistory() {
        Epic epic = new Epic("Эпик", "Эпическое описание", Status.NEW);
        taskManager.createTasks(epic);
        assertEquals(1, taskManager.getAllTasks().size());
        taskManager.deleteTask(epic.getId());
        FileBackedTasksManager load = FileBackedTasksManager.loadFromFile(file);
        assertEquals(List.of(), load.getAllTasks());
    }
}
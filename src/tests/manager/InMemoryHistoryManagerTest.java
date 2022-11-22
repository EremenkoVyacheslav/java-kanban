package tests.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.manager.HistoryManager;
import main.manager.InMemoryHistoryManager;
import main.task.status.Status;
import main.task.tasks.Epic;
import main.task.tasks.Subtask;
import main.task.tasks.Task;
import main.task.tasks.TaskType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {
   private HistoryManager historyManager ;
   private Task task;
   private Epic epic;
   private Subtask subtask;

    @BeforeEach
    void beforeEach() {
        historyManager  = new InMemoryHistoryManager();
        task = new Task(1, "Очевидно, что это Таска","Описание тасочки", Status.NEW, TaskType.TASK,
                LocalDateTime.of(2022, 11, 19, 15, 30), Duration.ofMinutes(5));

        epic = new Epic("Эпик", "Эпическое описание", Status.IN_PROGRESS);
        subtask = new Subtask(3, "Сабтаска, но это не точно", Status.NEW,
                "Описание Сабтаски", TaskType.SUBTASK,
                LocalDateTime.of(2022, 11, 19, 15, 40, 30),
                Duration.ofMinutes(10), 2);
    }

    @Test
    void addTaskInHistory() {
        historyManager.add(task);
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(1, history.size(), "История не пустая.");
    }

    @Test
    void duplicatingHistory() {
        historyManager.add(task);
        historyManager.add(task);
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(1, history.size(), "Дублирования не свершилось.");
    }

    @Test
    void removeTaskFromStart() {
        historyManager.add(task);
        historyManager.add(epic);
        historyManager.add(subtask);
        historyManager.remove(task.getId());
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(2, history.size(), "Задача не удалилась :(");
    }
    @Test
    void removeTaskFromMiddle() {
        historyManager.add(task);
        historyManager.add(epic);
        historyManager.add(subtask);
        historyManager.remove(epic.getId());
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(2, history.size(), "Задача не удалилась :(");
    }
    @Test
    void removeTaskFromEnd() {
        historyManager.add(task);
        historyManager.add(epic);
        historyManager.add(subtask);
        historyManager.remove(subtask.getId());
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(2, history.size(), "Задача не удалилась :(");
    }

}
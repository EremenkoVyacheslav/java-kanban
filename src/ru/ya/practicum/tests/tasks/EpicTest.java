package ru.ya.practicum.tests.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ya.practicum.manager.InMemoryTaskManager;
import ru.ya.practicum.manager.TaskManager;
import ru.ya.practicum.status.Status;
import ru.ya.practicum.tasks.Epic;
import ru.ya.practicum.tasks.Subtask;
import ru.ya.practicum.tasks.TaskType;


import static org.junit.jupiter.api.Assertions.*;

class EpicTest {
    protected TaskManager taskManager;
    private Epic epic;
    private Subtask subtask;

    @BeforeEach
    public void beforeEach() {
        taskManager = new InMemoryTaskManager();
        epic = new Epic(1, "Эпик", "Очень качественное описание эпика", Status.NEW, TaskType.EPIC);
        subtask = new Subtask("Сабтаска, но это не точно", "Описание Сабтаски", 2, Status.NEW);

    }

    @Test
    void shouldBeANewStatus() {
        taskManager.createEpics(epic);
        assertThrows(IndexOutOfBoundsException.class, () -> epic.getEpicsSubtask().get(1),
                "Сабтасочка не видит эпик");
        assertEquals(epic.getStatus(), Status.NEW, "Неправильный статус");
    }


    @Test
    void statusShouldBeDone() {
        subtask.setStatus(Status.DONE);
        epic.setStatus(Status.DONE);
        taskManager.createEpics(epic);
        taskManager.createSubtasks(subtask);
        Status epicStatus = epic.getStatus();
        assertEquals(Status.DONE, epicStatus, "Неправильный статус");
    }

    @Test
    void statusShouldBeDoneAndNew() {
        subtask.setStatus(Status.NEW);
        Subtask subtask1 = new Subtask("Сабтаска, но это не точно", "Описание Сабтаски", 2, Status.NEW);
        taskManager.updateEpic(epic);
        taskManager.createSubtasks(subtask);
        taskManager.createSubtasks(subtask1);
        epic.setStatus(Status.IN_PROGRESS);
        assertEquals(Status.IN_PROGRESS, epic.getStatus(), "Статус эпика должен быть IN_PROGRESS");
    }

    @Test
    void statusShouldBeInProgress() {
        taskManager.createEpics(epic);
        subtask.setStatus(Status.IN_PROGRESS);
        epic.setStatus(Status.IN_PROGRESS);
        assertEquals(Status.IN_PROGRESS, epic.getStatus(), "Статус эпика должен быть IN_PROGRESS");
    }

}
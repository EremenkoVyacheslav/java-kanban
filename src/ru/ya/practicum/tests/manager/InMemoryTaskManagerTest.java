package ru.ya.practicum.tests.manager;

import org.junit.jupiter.api.BeforeEach;
import ru.ya.practicum.manager.InMemoryTaskManager;


public class InMemoryTaskManagerTest extends TaskManagerTest<InMemoryTaskManager> {

    @BeforeEach
    public void beforeEach() {
        taskManager = new InMemoryTaskManager();

    }
}
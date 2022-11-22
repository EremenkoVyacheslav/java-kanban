package tests.manager;

import org.junit.jupiter.api.BeforeEach;
import main.manager.InMemoryTaskManager;


public class InMemoryTaskManagerTest extends TaskManagerTest<InMemoryTaskManager> {

    @BeforeEach
    public void beforeEach() {
        taskManager = new InMemoryTaskManager();

    }
}
package ru.yandex.praktikum;

import ru.yandex.praktikum.manager.TaskManager;
import ru.yandex.praktikum.status.Status;
import ru.yandex.praktikum.tasks.Epic;
import ru.yandex.praktikum.tasks.Subtask;
import ru.yandex.praktikum.tasks.Task;
import ru.yandex.praktikum.utils.Managers;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        printMenu();
    }

    public static void printMenu() {
        TaskManager taskManager = Managers.getInMemoryTaskManager(Managers.getDefaultHistory());
        List<Task> history = taskManager.getHistory();


        taskManager.createTasks(new Task(1, "Купить бублик", Status.NEW, "Очень большой бублик"));
        taskManager.createTasks(new Task(2, "Съездить на рыбалку", Status.NEW, "Поймать рыбу"));

        Subtask subtask = new Subtask(1, "Создать Задачи", Status.DONE, "Таски, Сабтаски, Эпики",
                3);
        Subtask subtask1 = new Subtask(2, "Создать Менеджер", Status.DONE, "Он за все отвечает",
                3);
        Subtask subtask2 = new Subtask(3, "Тут уже кончились идеи", Status.NEW,
                "Очень важное описание", 4);

        taskManager.createEpics(new Epic(1, "Написать Трекер Задач", "Для Яндекс Практикума"));
        taskManager.createEpics(new Epic(2, "Сдать проект", "Сдать до 15 числа"));

        taskManager.updateTask(new Task(1, "Satisfaction", Status.NEW, "Песня такая"));
        taskManager.updateSubtask(new Subtask(2, "Яндекс Музыка", Status.IN_PROGRESS, "Вообще зачет",
                2));
        taskManager.updateEpic(new Epic(2, "One, two, free, four", "Песня такая"));

        taskManager.createSubtasks(subtask);
        taskManager.createSubtasks(subtask1);
        taskManager.createSubtasks(subtask2);

        taskManager.getAllEpic();
        taskManager.getAllTasks();
        taskManager.getAllSubtask();

        taskManager.deleteTask(1);
        taskManager.deleteEpic(2);
        taskManager.deleteSubtask(5);

        taskManager.getTask(2);

        taskManager.getSubtask(2);
        taskManager.getSubtask(6);

        taskManager.getEpic(3);

        System.out.println(history);
    }
}

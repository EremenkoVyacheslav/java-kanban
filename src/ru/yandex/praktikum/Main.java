package ru.yandex.praktikum;

import ru.yandex.praktikum.manager.TaskManager;
import ru.yandex.praktikum.status.Status;
import ru.yandex.praktikum.tasks.Epic;
import ru.yandex.praktikum.tasks.Subtask;
import ru.yandex.praktikum.tasks.Task;
import ru.yandex.praktikum.utils.Managers;


public class Main {
    public static void main(String[] args) {
        printMenu();
    }

    public static void printMenu() {
        TaskManager taskManager = Managers.getInMemoryTaskManager(Managers.getDefaultHistory());


        taskManager.createTasks(new Task(1, "Купить бублик", Status.NEW, "Очень большой бублик"));
        taskManager.createTasks(new Task(2, "Съездить на рыбалку", Status.NEW, "Поймать рыбу"));

        taskManager.createEpics(new Epic(1, "Написать Трекер Задач", "Для Яндекс Практикума"));
        taskManager.createEpics(new Epic(2, "Сдать проект", "Сдать до 15 числа"));

        Subtask subtask = new Subtask(1, "Создать Задачи", Status.NEW, "Таски, Сабтаски, Эпики",
                3);
        Subtask subtask1 = new Subtask(2, "Создать Менеджер", Status.NEW, "Он за все отвечает",
                3);
        Subtask subtask2 = new Subtask(3, "Тут уже кончились идеи", Status.NEW,
                "Очень важное описание", 3);

        taskManager.createSubtasks(subtask);
        taskManager.createSubtasks(subtask1);
        taskManager.createSubtasks(subtask2);

        taskManager.getTask(1);
        taskManager.getTask(2);
        taskManager.getTask(1);
        taskManager.getTask(2);
        taskManager.getEpic(3);
        taskManager.getTask(1);
        taskManager.getSubtask(5);
        taskManager.getSubtask(6);
        taskManager.getSubtask(7);

        System.out.println(taskManager.getHistory());

        taskManager.remove(1);
        taskManager.remove(3);
        taskManager.remove(5);
        taskManager.remove(6);
        taskManager.remove(7);

        System.out.println(taskManager.getHistory());
    }
}

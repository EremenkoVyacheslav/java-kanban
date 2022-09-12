import manager.Manager;
import tasks.Epic;
import tasks.Status;
import tasks.Subtask;
import tasks.Task;

public class Main {
    public static void main(String[] args) {
        printMenu();
    }

    public static void printMenu() {
        Manager manager = new Manager();

        manager.createTasks(new Task(1, "Купить бублик", Status.NEW, "Очень большой бублик"));
        manager.createTasks(new Task(2, "Съездить на рыбалку", Status.NEW, "Поймать рыбу"));

        Subtask subtask = new Subtask(1, "Создать Задачи", Status.DONE, "Таски, Сабтаски, Эпики",
                3);
        Subtask subtask1 = new Subtask(2, "Создать Менеджер", Status.DONE, "Он за все отвечает",
                3);
        Subtask subtask2 = new Subtask(3, "Тут уже кончились идеи", Status.NEW,
                "Очень важное описание", 4);

        manager.createEpics(new Epic(1, "Написать Трекер Задач", "Для Яндекс Практикума"));
        manager.createEpics(new Epic(2, "Сдать проект", "Сдать до 15 числа"));

        manager.updateTask(new Task(1, "Satisfaction", Status.NEW, "Песня такая"));
        manager.updateSubtask(new Subtask(2, "Яндекс Музыка", Status.IN_PROGRESS, "Вообще зачет",
                2));
        manager.updateEpic(new Epic(2, "One, two, free, four", "Песня такая"));

        manager.createSubtasks(subtask);
        manager.createSubtasks(subtask1);
        manager.createSubtasks(subtask2);

        manager.getAllEpic();
        manager.getAllTasks();
        manager.getAllSubtask();

        manager.deleteTask(1);
        manager.deleteEpic(2);
        manager.deleteSubtask(5);
    }
}

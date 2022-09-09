public class Main {
    public static void main(String[] args) {
        printMenu();
    }

    public static void printMenu() {
        Manager manager = new Manager();

        manager.createTask(new Task("Купить бублик", "Очень большой бублик"));
        manager.createTask(new Task("Съездить на рыбалку", "Поймать рыбу"));

        manager.createTask(new Epic("Написать Трекер Задач", "Для Яндекс Практикума"));
        manager.createTask(new Subtask("Создать Задачи", "Таски, Сабтаски, Эпики", 3));
        manager.createTask(new Subtask("Создать Менеджер", "Он за все отвечает", 3));

        manager.createTask(new Epic("Сдать проект", "Сдать до 15 числа"));
        manager.createTask(new Subtask("Тут уже кончились идеи", "Очень важное описание", 6));

        manager.updateTask(new Task("Уехать жить в горы", "Далекие горы"), 1,
                "IN_PROGRESS");
        manager.updateTask(new Subtask("Купить барана", "Волосатого", 6),
                7, "DONE");

        manager.printAll();
        manager.getById(2);
        manager.printEpicTask(3);
        manager.deleteById(1);
        manager.deleteById(6);

    }
}
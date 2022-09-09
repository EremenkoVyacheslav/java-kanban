public class Subtask extends Task {
    int idEpic;

    public Subtask() {
    }

    public Subtask(String name, String description, int id) {
        super(name, description);
        this.idEpic = id;

    }

    @Override
    public String toString() {
        return "Subtask{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

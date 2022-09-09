public class Task {
    protected String name;
    protected String description;
    String status;

    public Task() {
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.status = "NEW";
    }

    public Object returnGetClass() {
        return this.getClass();
    }


}

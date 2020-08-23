package tasks;

public class Task {
    public String name;
    public int id;

    // empty constructor is necessary for de-serializing from JSON
    public Task (){}

    public Task(String name){
        this.name = name;
    }

    public void setId(int id){
        this.id = id;
    }
}

package tasks;

public class Task {
    private static int count = 0;
    public String name;
    public int id;
    public Task(String name){
        this.name = name;
        this.id = count;
        count++;
    }
}

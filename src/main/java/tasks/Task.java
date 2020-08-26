package tasks;

public class Task {
    public String name;
    public int id;
    public Category category;

    public Task(String name){
        this.name = name;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setCategory(Category cat) { this.category = cat; }
}

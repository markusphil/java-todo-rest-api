package tasks;

import java.sql.Date;
import java.sql.Timestamp;

public class Task {
    public String name;
    public int id;
    public Category category;
    public String description;
    public Timestamp createdAt;
    public Date dueTo;


    // empty constructor is necessary for de-serializing from JSON
    public Task (){}

    public Task(String name){
        this.name = name;
    }

    public void setId(int id){ this.id = id; }

    public void setCreatedAt(Timestamp createdDate){ this.createdAt = createdDate; }

    public void setDueTo(Date dueDate){ this.dueTo = dueDate; }

    public void setDescription(String description){ this.description = description; }

    public void setCategory(Category cat) { this.category = cat; }
}

package tasks;

import java.sql.Date;

public class Task {
    public String name;
    public int id;
    public Category category;
    public String description;
    public Date createdAt;
    public Date dueTo;

    public Task(String name){
        this.name = name;
    }

    public void setId(int id){ this.id = id; }

    public void setCreatedAt(Date createdDate){ this.createdAt = createdDate; }

    public void setDueTo(Date dueDate){ this.dueTo = dueDate; }

    public void setDescription(String description){ this.description = description; }

    public void setCategory(Category cat) { this.category = cat; }
}

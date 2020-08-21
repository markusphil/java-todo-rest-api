package tasks;

public interface ITask {
    void setStatus(boolean isDone);
    void setPriority(int priorityNumber);
    void setCategory(Category category);
}

package persistance;

import tasks.Task;

import java.sql.SQLException;
import java.util.List;

public interface ITaskDao {
    List<Task> get() throws SQLException;
    Task getById(int id) throws SQLException;
    void delete(int id) throws SQLException;
    Task update(Task object) throws SQLException;
    Task add(Task object) throws SQLException;

}

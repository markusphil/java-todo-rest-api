package persistance;

import tasks.Task;

import java.sql.SQLException;
import java.util.List;

public interface ITaskDao {
    List<Task> get() throws SQLException;
    //IDbObject getById(int id) throws SQLException;
    //void delete(int id) throws SQLException;
    //IDbObject update(IDbObject object) throws SQLException;
    Task add(Task object) throws SQLException;

}

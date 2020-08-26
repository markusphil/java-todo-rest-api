package persistance;

import exceptions.ResourceNotFoundException;
import tasks.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDao implements ITaskDao {

    static Connection con
            = DatabaseConnector.getConnection();

    @Override
    public List<Task> get() throws SQLException{
        String query = "select * from task";
        PreparedStatement ps
                = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Task> ls = new ArrayList<Task>();

        while (rs.next()) {
            Task task = new Task(rs.getString("name"));
            task.setId(rs.getInt("id"));
            ls.add(task);
        }
        return ls;
    }
    @Override
    public Task add(Task task) throws SQLException {
        String query = "insert into task(name) VALUES (?)";
        PreparedStatement ps
                = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, task.name);

        int affectedRows = ps.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                task.setId(generatedKeys.getInt(1));
            }
            else {
                throw new SQLException("Creating Task failed, no ID obtained.");
            }
        }

        return task;
    }

    @Override
    public Task update(Task task) throws SQLException {
        String query = "update task set name = ? where id = ?";
        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setString(1, task.name);
        ps.setInt(2, task.id);

        int affectedRows = ps.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Updating category failed, no rows affected.");
        }

        return task;
    }

    @Override
    public Task getById(int id) throws SQLException {
        String query = "select * from task where id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Task task = null;
        while(rs.next()){
            task = new Task(rs.getString("name"));
            task.setId(rs.getInt("id"));
        }
        if (task == null) throw new ResourceNotFoundException("Task");

        return task;

    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "delete from task where id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        int affectedRows = ps.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Deleting task failed, no rows affected.");
        }
    }
    
}

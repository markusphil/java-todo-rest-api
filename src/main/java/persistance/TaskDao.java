package persistance;

import exceptions.ResourceNotFoundException;
import tasks.Category;
import tasks.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDao implements ITaskDao {

    static Connection con
            = DatabaseConnector.getConnection();

    @Override
    public List<Task> get() throws SQLException{
        String query = "SELECT task.*, category.name AS cat_name, category.colorCode AS cat_color FROM task LEFT OUTER JOIN category ON task.c_id = category.id ";
        PreparedStatement ps
                = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Task> ls = new ArrayList<>();

        while (rs.next()) {
            Task task = new Task(rs.getString("name"));
            task.setId(rs.getInt("id"));

            String catName = rs.getString("cat_name");
            if(catName != null){
                task.setCategory(new Category(catName, rs.getString("cat_color")));
            }

            task.setDescription(rs.getString("description"));
            task.setDueTo(rs.getDate("dueTo"));
            task.setCreatedAt(rs.getTimestamp("createdAt"));

            ls.add(task);
        }
        return ls;
    }
    @Override
    public Task add(Task task) throws SQLException {
        String query = "insert into task(name, description, c_id, dueTo) VALUES (?, ?, ?, ?)";
        PreparedStatement ps
                = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, task.name);

        if(task.description != null){
            ps.setString(2,task.description);
        } else {
            ps.setNull(2, Types.VARCHAR);
        }

        if(task.category != null){
            ps.setInt(3,task.category.id);
        } else {
            ps.setNull(3, Types.INTEGER);
        }

        if(task.dueTo != null){
            ps.setDate(4, task.dueTo);
        } else {
            ps.setNull(4, Types.DATE);
        }

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

    // TODO: extend update to handle optional fields.
    @Override
    public Task update(Task task) throws SQLException {
        String query = "update task set name = ? c_id = ? where id = ?";
        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setString(1, task.name);
        ps.setInt(3, task.id);
        if (task.category != null) ps.setInt(2, task.category.id);

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
            String catName = rs.getString("cat_name");
            if(catName != null){
                task.setCategory(new Category(catName, rs.getString("cat_color")));
            }
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

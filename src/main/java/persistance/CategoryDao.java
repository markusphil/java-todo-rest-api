package persistance;

import exceptions.ResourceNotFoundException;
import tasks.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao implements ICategoryDao {

    static Connection con
            = DatabaseConnector.getConnection();

    @Override
    public List<Category> get() throws SQLException{
        String query = "select * from category";
        PreparedStatement ps
                = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Category> ls = new ArrayList<>();

        while (rs.next()) {
            Category cat = new Category(rs.getString("name"),rs.getString("colorCode"));
            cat.setId(rs.getInt("id"));
            ls.add(cat);
        }
        return ls;
    }

    @Override
    public Category add(Category cat) throws SQLException {
        String query = "insert into category(name, colorCode) VALUES (?,?)";
        PreparedStatement ps
                = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, cat.name);
        ps.setString(2, cat.color.value);

        int affectedRows = ps.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating category failed, no rows affected.");
        }

        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                cat.setId(generatedKeys.getInt(1));
            }
            else {
                throw new SQLException("Creating Category failed, no ID obtained.");
            }
        }

        return cat;
    }

    @Override
    public Category update(Category cat) throws SQLException {
        String query = "update category set name = ?, colorCode = ? where id = ?";
        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setString(1, cat.name);
        ps.setString(2, cat.color.value);
        ps.setInt(3, cat.id);

        int affectedRows = ps.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Updating category failed, no rows affected.");
        }

        return cat;
    }

    @Override
    public Category getById(int id) throws SQLException {
        String query = "select * from category where id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Category cat = null;
        while(rs.next()){
            cat = new Category(rs.getString("name"), rs.getString("colorCode"));
            cat.setId(rs.getInt("id"));
        }
        if (cat == null) throw new ResourceNotFoundException("Category");

        return cat;

    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "delete from category where id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        int affectedRows = ps.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Deleting category failed, no rows affected.");
        }
    }
    
}

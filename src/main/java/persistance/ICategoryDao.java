package persistance;

import tasks.Category;


import java.sql.SQLException;
import java.util.List;

public interface ICategoryDao {
    List<Category> get() throws SQLException;
    //Category getById(int id) throws SQLException;
    //void delete(int id) throws SQLException;
    Category update(Category object) throws SQLException;
    Category add(Category object) throws SQLException;

}

package persistance;

import rest.Application;
import tasks.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Setup {
    private static Connection con = null;

    private static void clearTables(){
        try {
            String query = "drop table if exists task, category";
            PreparedStatement ps = con.prepareStatement(query);
            ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private static void createCategoryTable() throws SQLException {
        String query = "create table if not exists category(" +
                "id int primary key auto_increment," +
                "name varchar(400)," +
                "colorCode varchar (7))";
        PreparedStatement ps = con.prepareStatement(query);
        ps.executeUpdate();
    }

    private static void createTaskTable() throws SQLException {
        String query =  "create table if not exists task(" +
                        "id int primary key auto_increment, name varchar(400),c_id int," +
                        "constraint task_fk_c_id foreign key(c_id) references category(id))";
        PreparedStatement ps = con.prepareStatement(query);
        ps.executeUpdate();
    }

    private static void createDefaultCategory() throws SQLException {
        CategoryDao dao = new CategoryDao();
        dao.add(new Category("General"));
    }

    public static void main(String[] args) throws SQLException{

        // establish Database connection
        DatabaseConnector.connect();
        con = DatabaseConnector.getConnection();

        // start tasks
        clearTables();
        createCategoryTable();
        createTaskTable();
        createDefaultCategory();

        con.close();

        // run Springboot Application
        Application.main(new String[]{});


    }



}

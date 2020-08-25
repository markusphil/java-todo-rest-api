package rest;

import org.springframework.boot.actuate.endpoint.InvalidEndpointRequestException;
import org.springframework.web.bind.annotation.*;
import persistance.CategoryDao;
import tasks.Category;

import java.sql.SQLException;
import java.util.List;

@RestController
public class CategoryController {

    static CategoryDao dao = new CategoryDao();

    @GetMapping("/category")
    public List<Category> getCategories() throws SQLException {
        return dao.get();
    }

    @PostMapping("/category")
    public Category addCategory(@RequestBody Category cat) throws SQLException{
        return dao.add(cat);
    }

    @PostMapping("/category/{id}")
    public Category updateCategory(@RequestBody Category cat, @PathVariable int id) throws SQLException{
        if (id == cat.id){
            return dao.update(cat);
        } else {
            throw new InvalidPathException("Received different id in Path and body");
        }
    }
}

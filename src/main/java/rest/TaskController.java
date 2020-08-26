package rest;

import exceptions.InvalidPathException;
import exceptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import persistance.TaskDao;
import tasks.Task;

import java.sql.SQLException;
import java.util.List;

@RestController
public class TaskController {

    static TaskDao dao = new TaskDao();

    @GetMapping("/task")
    public List<Task> getTasks() throws SQLException {
        return dao.get();
    }

    @GetMapping("/task/{id}")
    public Task getTask(@PathVariable int id) throws SQLException, ResourceNotFoundException {
        return dao.getById(id);
    }

    @PostMapping("/task")
    public Task addTask(@RequestBody Task newTask) throws SQLException {
        return dao.add(newTask);
    }

    @PostMapping("/task/{id}")
    public Task updateTask(@RequestBody Task task, @PathVariable int id) throws SQLException {
        if (id != task.id){
            throw new InvalidPathException("Received different ids in Path and Body");
        } else {
            return dao.update(task);
        }

    }

}


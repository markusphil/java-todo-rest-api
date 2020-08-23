package rest;

import org.springframework.web.bind.annotation.*;

import persistance.TaskDao;
import tasks.Task;

import java.sql.SQLException;
import java.util.List;

@RestController
public class TaskController {

    static TaskDao dao = new TaskDao();

    @GetMapping("/task")
    public List<Task> getTask() throws SQLException{
        return dao.get();
    }
    @PostMapping("/task")
    public Task addTask(@RequestBody Task newTask) throws SQLException{
        System.out.println(newTask.id + ": " +  newTask.name);
        return dao.add(newTask);
    }
}


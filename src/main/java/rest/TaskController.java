package rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tasks.Task;

@RestController
public class TaskController {

    @GetMapping("/task")
    public Task getTask(@RequestParam(value = "name", defaultValue = "A random Task") String name){
         return new Task(name);
    }
}


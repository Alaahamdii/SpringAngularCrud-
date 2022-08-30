package com.proj0.hello.controller;

import com.proj0.hello.entity.Task;
import com.proj0.hello.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1")

public class TaskController
{
    @Autowired
    private TaskService taskService;

    // get all tasks
    @GetMapping("/tasks")
    public List<Task> getTasks()
    {
        return taskService.getTasks();
    }

    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable Long id)
    {
        return taskService.getTaskByiId(id).
                orElseThrow
                (
                        ()->new EntityNotFoundException("Requested task not found")
                );
    }

    @PostMapping ("/tasks")
    public Task addTask(@RequestBody Task task)
    {
        return taskService.save(task);
    }


    @PutMapping("tasks/{id}")
    public ResponseEntity<?> addTask(@RequestBody Task task, @PathVariable Long id)
    {
        if(taskService.existById(id))
        {
            Task task1=taskService.getTaskByiId(id).orElseThrow
                    (
                            ()-> new EntityNotFoundException("Requested task not found !! ")
                    );
            task1.setTitle(task.getTitle());
            task1.setDescription(task.getDescription());
            task1.setDueDate(task.getDueDate());
            task1.setType(task.getType());
            return ResponseEntity.ok().body(task1);
        }
        else
        {
            HashMap<String ,String> message =new HashMap<>();
            message.put("message ",id+"task not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @DeleteMapping("tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id)
    {
        if(taskService.existById(id))
        {
            taskService.deleteTask(id);
            HashMap<String,String> message =new HashMap<>();
            message.put("message ","Task with id "+id+" deleted successfully");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        else
        {
            HashMap<String ,String> message =new HashMap<>();
            message.put("message "," task with id "+id+" not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
}

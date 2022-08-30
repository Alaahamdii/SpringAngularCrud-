package com.proj0.hello.service;


import com.proj0.hello.entity.Task;
import com.proj0.hello.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class TaskService
{
    private TaskRepository taskRepository;
    /*  @Value("task.name")
    String TaskName;*/


    public List<Task> getTasks()
    {
        return taskRepository.findAll();
    }


    public Optional<Task> getTaskByiId(Long id)
    {
        return taskRepository.findById(id);
    }

    public Task save(Task task)
    {
        return taskRepository.saveAndFlush(task);
    }

    public boolean existById(Long id)
    {
        return taskRepository.existsById(id);
    }

    public void deleteTask (Long id)
    {
         taskRepository.deleteById(id);
    }
}

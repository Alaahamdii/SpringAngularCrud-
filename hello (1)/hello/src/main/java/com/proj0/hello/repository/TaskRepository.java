package com.proj0.hello.repository;

import com.proj0.hello.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository <Task, Long>
{

}

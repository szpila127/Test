package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.service.DbService;
import com.crud.tasks.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/tasks")
@CrossOrigin("*")
public class TaskController {

    @Autowired
    private DbService service;

    @Autowired
    private TaskMapper taskMapper;

    @GetMapping
    public List<TaskDto> getTasks() {
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    @GetMapping(value = "/{id}")
    public TaskDto getTask(@PathVariable("id") Long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(service.getTask(taskId).orElseThrow(TaskNotFoundException::new));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTask(@PathVariable("id") Long taskId) {
        service.deleteTask(taskId);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        return taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
        service.saveTask(taskMapper.mapToTask(taskDto));
    }
}


package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void testGetEmptyTasks() throws Exception {
        //Given
        List<TaskDto> tasksDto = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();

        when(service.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(tasksDto);

        //When & Then
        mockMvc.perform(get("/v1/task").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void testGetTasks() throws Exception {
        //Given
        List<TaskDto> listDto = new ArrayList<>();
        TaskDto taskDto = new TaskDto(1L, "title_test", "content_test");
        TaskDto taskDto2 = new TaskDto(2L, "title_test2", "content_test2");
        listDto.add(taskDto);
        listDto.add(taskDto2);

        List<Task> list = new ArrayList<>();
        Task task = new Task(1L, "title_test", "content_test");
        Task task2 = new Task(2L, "title_test2", "content_test2");
        list.add(task);
        list.add(task2);

        when(taskMapper.mapToTaskDtoList(list)).thenReturn(listDto);
        when(service.getAllTasks()).thenReturn(list);

        //When & Then
        mockMvc.perform(get("/v1/task/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("title_test")))
                .andExpect(jsonPath("$[0].content", is("content_test")));
    }

    @Test
    public void testGetTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(10L, "title_test", "content_test");
        Task task = new Task(10L, "title_test", "content_test");

        when(service.getTask(10L)).thenReturn(java.util.Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(get("/v1/task/10").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("title_test")));
    }

    @Test
    public void testDeleteTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(10L, "title_test", "content_test");
        doNothing().when(service).deleteTask(10L);

        //When & Then
        mockMvc.perform(delete("/v1/task/10").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(service, times(1)).deleteTask(taskDto.getId());
    }

    @Test
    public void testUpdateTask() throws Exception {
        //Given
        Task task = new Task(10L, "title", "content_test");
        TaskDto taskDto = new TaskDto(10L, "title", "content_test");

        when(taskMapper.mapToTaskDto(ArgumentMatchers.any(Task.class))).thenReturn(taskDto);
        when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(task);
        when(service.saveTask(ArgumentMatchers.any(Task.class))).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(put("/v1/task")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(10)));
        verify(service, times(1)).saveTask(task);
    }

    @Test
    public void testCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(13L, "title", "content_test");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(post("/v1/task")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}

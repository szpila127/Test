package com.crud.tasks.mapper.task;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskMapperTestSuite {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title1", "content1");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        Assert.assertEquals("title1", task.getTitle());
        Assert.assertEquals("content1", task.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "title1", "content1");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        Assert.assertEquals("title1", taskDto.getTitle());
        Assert.assertEquals("content1", taskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        Task task1 = new Task(1L, "title1", "content1");
        Task task2 = new Task(2L, "title2", "content2");
        List<Task> list = new ArrayList<>();
        list.add(task1);
        list.add(task2);
        //When
        List<TaskDto> listDto = taskMapper.mapToTaskDtoList(list);
        //Then
        Assert.assertEquals("title1", listDto.get(0).getTitle());
        Assert.assertEquals("content2", listDto.get(1).getContent());
    }
}

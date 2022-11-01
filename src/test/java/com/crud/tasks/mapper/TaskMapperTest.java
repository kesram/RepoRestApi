package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaskMapperTest {

    TaskMapper taskMapper = new TaskMapper();

    @Test
    public void shouldMapToTask(){
        //Given
        TaskDto taskDto = new TaskDto(1L,"title","content");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(1L,task.getId());
        assertEquals("title", task.getTitle());
        assertEquals("content", task.getContent());
    }

    @Test
    public void shouldMapToTaskDto(){
        //Given
        Task task = new Task(1L,"title","content");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(1L,taskDto.getId());
        assertEquals("title", taskDto.getTitle());
        assertEquals("content", taskDto.getContent());
    }

    @Test
    public void shouldMapToTaskDtoList(){
        //Given
        List<Task> taskList = new ArrayList<>();
        Task task = new Task(1L,"title","content");
        taskList.add(task);
        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertEquals(1L,taskDtoList.get(0).getId());
        assertEquals("title",taskDtoList.get(0).getTitle());
        assertEquals("content",taskDtoList.get(0).getContent());
        assertEquals(1,taskList.size());
    }
}

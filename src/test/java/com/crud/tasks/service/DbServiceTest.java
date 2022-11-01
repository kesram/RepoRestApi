package com.crud.tasks.service;

import com.crud.tasks.controller.TaskController;
import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest
public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void getAllTasksTest() {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "task 1", "content 1"));
        tasks.add(new Task(2L, "task 2", "content 2"));
        when(taskRepository.findAll()).thenReturn(tasks);

        //When
        List<Task> tasksList = dbService.getAllTasks();

        //Then
        assertEquals(2, tasksList.size());
    }

    @Test
    public void getTaskTest() throws TaskNotFoundException {
        //Given
        Task task = new Task(1L, "task 1", "content 1");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        //When
        Task foundedTask = dbService.getTask(1L);

        //Then
        assertEquals(task, foundedTask);
    }

    @Test
    public void saveTask() {
        //Given
        Task task = new Task(1L, "task 1", "content 1");
        when(dbService.saveTask(task)).thenReturn(task);

        //When
        Task savedTask = dbService.saveTask(task);

        //Then
        assertEquals(task.getId(), savedTask.getId());
        assertEquals(task.getTitle(), savedTask.getTitle());
        assertEquals(task.getContent(), savedTask.getContent());
    }

    @Test
    public void deleteTask() {
        //Given

        //When
        dbService.deleteById(1L);

        //Then
        verify(taskRepository, times(1)).deleteById(1L);
    }
}

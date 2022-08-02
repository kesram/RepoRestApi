package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final DbService service;
    private final TaskMapper taskMapper;


//    @RequestMapping(method = RequestMethod.GET, value ="")
    @GetMapping
    public List<TaskDto> getTasks(){
        List<Task> tasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }

    @GetMapping(value = "{taskId}")
    public TaskDto getTask(@PathVariable Long taskId)throws Exception{
        return taskMapper.mapToTaskDto(service.getTask(taskId).orElseThrow(Exception::new));
    }

    @DeleteMapping
    public void deleteTask(Long taskId){

    }

    @PutMapping
    public TaskDto updateTask(TaskDto task) {
        return new TaskDto(1L,"update title","update_content");
    }

    @PostMapping
    public void createTask(@RequestBody TaskDto taskDto){
        Task task = taskMapper.mapToTask(taskDto);
        service.saveTask(task);
    }
}

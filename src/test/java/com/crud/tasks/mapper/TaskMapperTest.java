package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskMapperTest {

    private TaskMapper taskMapper = new TaskMapper();

    @Test
    public void mapperTaskTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Test", "test");
        Task task = new Task(1L,"Test", "test");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        //When
        Task mapTask = taskMapper.mapToTask(taskDto);
        TaskDto mapTaskDto = taskMapper.mapToTaskDto(task);
        List<TaskDto> mapTaskListsDto = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertEquals(taskDto.getId(),mapTask.getId());
        assertEquals(taskDto.getTitle(),mapTask.getTitle());
        assertEquals(taskDto.getContent(),mapTask.getContent());
        assertEquals(task.getId(),mapTaskDto.getId());
        assertEquals(task.getTitle(),mapTaskDto.getTitle());
        assertEquals(task.getContent(),mapTaskDto.getContent());
        assertEquals(taskList.get(0).getId(),mapTaskListsDto.get(0).getId());
        assertEquals(taskList.get(0).getTitle(),mapTaskListsDto.get(0).getTitle());
        assertEquals(taskList.get(0).getContent(),mapTaskListsDto.get(0).getContent());
    }
}

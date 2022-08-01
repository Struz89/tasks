package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DbServiceTest {

    @Autowired
    private DbService dbService;

    @Test
    public void getTasksTitleAndContentTest() throws TaskNotFoundException {
        //Given
        Task task1 = new Task(1L,"test1", "Test1");
        Task task2 = new Task(2L,"test2", "Test2");
        //When
        dbService.saveTask(task1);
        dbService.saveTask(task2);
        List<Task> taskList = dbService.getAllTasks();
        Long task1Id = taskList.get(0).getId();
        Long task2Id = taskList.get(1).getId();
        String task1Title = dbService.getTask(task1Id).getTitle();
        String task1Content = dbService.getTask(task1Id).getContent();
        String task2Title = dbService.getTask(task2Id).getTitle();
        String task2Content = dbService.getTask(task2Id).getContent();
        //Then
        assertEquals("test1", task1Title);
        assertEquals("Test1", task1Content);
        assertEquals("test2", task2Title);
        assertEquals("Test2", task2Content);
        assertEquals(2,taskList.size());
        //CleanUp
        dbService.deleteTask(task1Id);
        dbService.deleteTask(task2Id);
    }
}
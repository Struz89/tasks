package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private DbService service;

    @SpyBean
    private TaskMapper taskMapper;

    @Test
    public void shouldGetAllTasksTest() throws Exception {
        //Given
        Task task1 = new Task(1L,"test1", "Test1");
        Task task2 = new Task(2L,"test2", "Test2");
        service.saveTask(task1);
        service.saveTask(task2);
        List<Task> taskList = service.getAllTasks();
        Long task1Id = taskList.get(0).getId();
        Long task2Id = taskList.get(1).getId();

        //When & Then
        mockMvc.
                perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("test1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("Test1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title", Matchers.is("test2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].content", Matchers.is("Test2")));

        //CleanUp
        service.deleteTask(task1Id);
        service.deleteTask(task2Id);
    }

    @Test
    public void shouldAddTaskTest() throws Exception {
        //Given
        Task task1 = new Task(1L,"test1", "Test1");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task1);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));

        //CleanUp
        List<Task> taskList = service.getAllTasks();
        Long task1Id = taskList.get(0).getId();
        service.deleteTask(task1Id);
    }

    @Test
    public void shouldGetTaskTest() throws Exception {
        //Given
        Task task1 = new Task(1L,"test1", "Test1");
        Task task2 = new Task(2L,"test2", "Test2");
        service.saveTask(task1);
        service.saveTask(task2);
        List<Task> taskList = service.getAllTasks();
        Long task1Id = taskList.get(0).getId();
        Long task2Id = taskList.get(1).getId();
        String uriTemplate = "/v1/tasks/" + task1Id.toString();

        //When & Then
        mockMvc.
                perform(MockMvcRequestBuilders
                        .get(uriTemplate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("test1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("Test1")));

        //CleanUp
        service.deleteTask(task1Id);
        service.deleteTask(task2Id);
    }

    @Test
    public void shouldDeleteTaskTest() throws Exception {
        //Given
        Task task1 = new Task(1L,"test1", "Test1");
        service.saveTask(task1);
        List<Task> taskList = service.getAllTasks();
        Long task1Id = taskList.get(0).getId();
        String uriTemplate = "/v1/tasks/" + task1Id.toString();

        //When & Then
        mockMvc.
                perform(MockMvcRequestBuilders
                        .delete(uriTemplate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldUpdateTaskTest() throws Exception {
        //Given
        Task task1 = new Task(1L,"test1", "Test1");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task1);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));

        //CleanUp
        List<Task> taskList = service.getAllTasks();
        Long task1Id = taskList.get(0).getId();
        service.deleteTask(task1Id);
    }
}
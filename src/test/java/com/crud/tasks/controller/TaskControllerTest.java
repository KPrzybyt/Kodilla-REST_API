package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    public void getTasks() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L,"Title", "Content");
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(taskDto);

        when(taskMapper.mapToTaskDtoList(service.getAllTasks())).thenReturn(taskDtoList);

        // When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)) //or isOk()
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].title",is("Title")))
                .andExpect(jsonPath("$[0].content",is("Content")))
        ;
    }
    @Ignore
    @Test
    public void getTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto((long)1,"Title", "Content");

        Task task = new Task((long)1,"Title", "Content");
        service.saveTask(task);

        Long taskId = (long)1;
        when(taskMapper.mapToTaskDto(service.getTask(taskId).orElse(task))).thenReturn(taskDto);

        // When & Then
        mockMvc.perform(get("/v1/task/getTask?taskId=(long)1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is((long)1)))
                .andExpect(jsonPath("$.title",is("Title")))
                .andExpect(jsonPath("$.content",is("Content")));
    }
    @Test(expected = TaskNotFoundException.class)
    public void getEmptyTask() throws Exception {
        //Given
        Long taskId = 1L;
        when(taskMapper.mapToTaskDto(service.getTask(taskId).orElseThrow((TaskNotFoundException::new)))).thenReturn(null);

        // When & Then
        mockMvc.perform(get("/v1/task/getTask?taskId=1L").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void deleteTask() throws Exception {
    }

    @Test
    public void updateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L,"Title", "Content");
        when(taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)))).thenReturn(taskDto);

        Gson gson = new Gson();

        String jsonContent = gson.toJson(taskDto);

        // When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.title",is("Title")))
                .andExpect(jsonPath("$.content",is("Content")))
        ;


    }

    @Test
    public void createTask() throws Exception {
    }

}
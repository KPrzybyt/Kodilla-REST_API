package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class MapperTestSuite {
    @InjectMocks
    private TrelloMapper trelloMapper;

    @InjectMocks
    private TaskMapper taskMapper;


    @Test
    public void mapToCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("to do", "things to do","first", "1");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("to do", trelloCard.getName());
        assertEquals("things to do", trelloCard.getDescription());
        assertEquals("first", trelloCard.getPos());
        assertEquals("1", trelloCard.getListId());
    }

    @Test
    public void mapToCardDtoTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("to do", "things to do","first", "1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("to do", trelloCardDto.getName());
        assertEquals("things to do", trelloCardDto.getDescription());
        assertEquals("first", trelloCardDto.getPos());
        assertEquals("1", trelloCardDto.getListId());
    }
    @Test
    public void mapToListTest() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1","To do",true);
        //When
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(trelloListDto);
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);
        TrelloList trelloList = trelloLists.get(0);
        //Then
        assertEquals("1", trelloList.getId());
        assertEquals("To do", trelloList.getName());
        assertTrue(trelloList.isClosed());

    }

    @Test
    public void mapToListDtoTest() {
        //Given
        TrelloList trelloList = new TrelloList("1","To do",true);
        //When
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);
        TrelloListDto trelloListDto = trelloListsDto.get(0);
        //Then
        assertEquals("1", trelloListDto.getId());
        assertEquals("To do", trelloListDto.getName());
        assertTrue(trelloListDto.isClosed());
    }

    @Test
    public void mapToBoardTest() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1","To do",true);
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(trelloListDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("exercises","1",trelloListsDto);
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(trelloBoardDto);
        //When

        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);
        TrelloBoard trelloBoard = trelloBoards.get(0);
        //Then
        assertFalse(trelloBoards.isEmpty());
        assertFalse(trelloBoard.getLists().isEmpty());
        assertEquals("1", trelloBoard.getId());
        assertEquals("exercises", trelloBoard.getName());

    }
    @Test
    public void mapToBoardDtoTest() {
        //Given
        TrelloList trelloList = new TrelloList("1","To do",true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);
        TrelloBoard trelloBoard = new TrelloBoard("exercises","1",trelloLists);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);
        //When

        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);
        TrelloBoardDto trelloBoardDto = trelloBoardsDto.get(0);
        //Then
        assertFalse(trelloBoardsDto.isEmpty());
        assertFalse(trelloBoardDto.getLists().isEmpty());
        assertEquals("1", trelloBoardDto.getId());
        assertEquals("exercises", trelloBoardDto.getName());

    }

    @Test
    public void mapToTaskTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L,"physics", "Science");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(1L, task.getId().longValue());
        assertEquals("physics", task.getTitle());
        assertEquals("Science", task.getContent());
    }
    @Test
    public void mapToTaskDtoTest() {
        //Given
        Task task = new Task(1L,"physics", "Science");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(1L, taskDto.getId().longValue());
        assertEquals("physics", taskDto.getTitle());
        assertEquals("Science", taskDto.getContent());
    }
    @Test
    public void mapToTaskDtoListTest() {
        //Given
        Task task = new Task(1L,"physics", "Science");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        TaskDto taskDto = taskDtoList.get(0);
        //Then
        assertFalse(taskDtoList.isEmpty());
        assertEquals(1L, taskDto.getId().longValue());
        assertEquals("physics", taskDto.getTitle());
        assertEquals("Science", taskDto.getContent());

    }
}

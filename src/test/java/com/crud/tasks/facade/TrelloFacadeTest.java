package com.crud.tasks.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTest {
    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloMapper trelloMapper;

    @Test
    public void createCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test", "tests", "2", "1");
        TrelloCard trelloCard = new TrelloCard("test", "tests", "2", "1");
        CreatedTrelloCardDto createdTrelloCard = new CreatedTrelloCardDto("1","size","xxx");

        when(trelloMapper.mapToCard(trelloCardDto)).thenReturn(trelloCard);
        when(trelloService.createTrelloCard(trelloMapper.mapToCardDto(trelloCard))).thenReturn(createdTrelloCard);

        //When
        CreatedTrelloCardDto createdTrelloCardDto = trelloFacade.createCard(trelloCardDto);

        //Then
        assertNotNull(createdTrelloCardDto);
        assertEquals("1", createdTrelloCardDto.getId());
        assertEquals("size", createdTrelloCardDto.getName());
        assertEquals("xxx", createdTrelloCardDto.getShortUrl());
    }

    @Test
    public void shouldFetchEmptyList() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1","test_list",false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("test","1", trelloLists));

        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1","test_list",false));

        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("test","1", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(new ArrayList<>());
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(new ArrayList<>());
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();
        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(0, trelloBoardDtos.size());
    }
    @Test
    public void shouldFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1","my_list",false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("my_task","1", trelloLists));

        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1","my_list",false));

        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("my_task","1", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoards);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(1, trelloBoardDtos.size());

        trelloBoardDtos.forEach(trelloBoardDto -> {
            assertEquals("1",trelloBoardDto.getId());
            assertEquals("my_task", trelloBoardDto.getName());

            trelloBoardDto.getLists().forEach(trelloListDto -> {
                assertEquals("1", trelloListDto.getId());
                assertEquals("my_list", trelloListDto.getName());
                assertEquals(false, trelloListDto.isClosed());
            });
        });
    }
}

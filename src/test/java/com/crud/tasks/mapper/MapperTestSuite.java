package com.crud.tasks.mapper;

import com.crud.tasks.client.TrelloClient;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MapperTestSuite {
    @InjectMocks
    private TrelloMapper trelloMapper;


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

}

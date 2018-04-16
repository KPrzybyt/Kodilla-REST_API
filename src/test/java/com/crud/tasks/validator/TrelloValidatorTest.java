package com.crud.tasks.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTest {

    @InjectMocks
    private TrelloValidator trelloValidator;

    @Test
    public void validateCardWithTestTest() {

        //Given
        TrelloCard trelloCard = new TrelloCard
                ("test", "names", "1","2");
        //When
        //Then
        trelloValidator.validateCard(trelloCard);
    }
    @Test
    public void validateCardWithoutTestTest() {

        //Given
        TrelloCard trelloCard = new TrelloCard
                ("name", "names", "1","2");
        //When
        //Then
        trelloValidator.validateCard(trelloCard);
    }
    @Test
    public void validateTrelloBoardsTest() {
        //Given
        TrelloList trelloList = new TrelloList("2","Do",true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);

        TrelloBoard trelloBoard = new TrelloBoard("1","test",trelloLists);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);
        //When
        //Then
        trelloValidator.validateTrelloBoards(trelloBoards);


    }
}

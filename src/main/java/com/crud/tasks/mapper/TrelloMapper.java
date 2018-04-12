package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class TrelloMapper {

    public TrelloBoard mapToBoard(TrelloBoardDto trelloBoardDto) {
        return new TrelloBoard(trelloBoardDto.getId(), trelloBoardDto.getName(), mapToList(trelloBoardDto.getLists()));
    }

    public List<TrelloBoard> mapToBoards(List<TrelloBoardDto> trelloBoardDto) {
        return trelloBoardDto.stream()
                .map(trelloBoard ->
                new TrelloBoard(trelloBoard.getId(), trelloBoard.getName(), mapToList(trelloBoard.getLists())))
                .collect(toList());
    }
    public List<TrelloBoardDto> mapToBoardsDto(List<TrelloBoard> trelloBoards) {
        return trelloBoards.stream()
                .map(trelloBoard ->
                new TrelloBoardDto(trelloBoard.getId(), trelloBoard.getName(), mapToListDto(trelloBoard.getLists())))
                .collect(toList());
    }

    public List<TrelloList> mapToList(final List<TrelloListDto> trelloListDto) {
        return trelloListDto.stream()
                .map(trelloList -> new TrelloList(trelloList.getId(),trelloList.getName(), trelloList.isClosed()))
                .collect(toList());
    }
    public List<TrelloListDto> mapToListDto(final List<TrelloList> trelloLists) {
        return trelloLists.stream()
                .map(trelloList -> new TrelloListDto(trelloList.getId(),trelloList.getName(), trelloList.isClosed()))
                .collect(toList());
    }
}

package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public void getTrelloBoards()  {

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

//        trelloBoards.stream()
//                .filter(t -> t.getName().contains("Kodilla")&!(t.getId().isEmpty()))
//                .map(l -> l.getId()+" "+l.getName())
//                .forEach(System.out::println);

        System.out.println(Optional.ofNullable(trelloBoards.stream()
                .filter(t -> t.getName().contains("Kodilla")&!(t.getId().isEmpty()))
                .map(l -> l.getId()+" "+l.getName())
                .collect(Collectors.joining("\n","","")))
                .orElse("Not found!"));


//        trelloBoards.forEach(trelloBoardDto -> System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName()));

    }
}
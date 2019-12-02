package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.cards.CreatedTrelloCard;
import com.crud.tasks.domain.cards.CreatedTrelloCard223;
import com.crud.tasks.domain.cards.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
@CrossOrigin("*")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @GetMapping(value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {

        return trelloClient.getTrelloBoards();

//        trelloBoards.stream()
//                .filter(boardDto -> boardDto.getId() != null)
//                .filter(boardDto -> boardDto.getName() != null)
//                .filter(boardDto -> boardDto.getName().contains("Kodilla"))
//                .forEach(trelloBoardDto -> {
//
//                    System.out.println(trelloBoardDto.getId() + " - " + trelloBoardDto.getName());
//
//                    System.out.println("This board contains lists: ");
//
//                    trelloBoardDto.getLists().forEach(trelloListDto ->
//
//                            System.out.println(trelloListDto.getName() + " - " + trelloListDto.getId()
//                                    + " - " + trelloListDto.isClosed()));
//                });
    }

    @PostMapping(value = "createTrelloCard")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {

        return trelloClient.createNewCard(trelloCardDto);
    }

    @PostMapping(value = "createTrelloCard223")
    public CreatedTrelloCard223 createTrelloCard223(@RequestBody TrelloCardDto trelloCardDto) {

        return trelloClient.createNewCard223(trelloCardDto);
    }
}
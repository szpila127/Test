package com.crud.tasks.mapper.trello;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.domain.cards.TrelloCard;
import com.crud.tasks.domain.cards.TrelloCardDto;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("id1", "name1", new ArrayList<>());
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("id2", "name2", new ArrayList<>());
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(trelloBoardDto1);
        trelloBoardsDto.add(trelloBoardDto2);
        //When
        List<TrelloBoard> mappedTrelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);
        //Then
        assertEquals("name1", mappedTrelloBoards.get(0).getName());
        assertEquals(2, mappedTrelloBoards.size());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        TrelloBoard trelloBoard1 = new TrelloBoard("id1", "name1", new ArrayList<>());
        TrelloBoard trelloBoard2 = new TrelloBoard("id2", "name2", new ArrayList<>());
        TrelloBoard trelloBoard3 = new TrelloBoard("id3", "name3", new ArrayList<>());
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard1);
        trelloBoards.add(trelloBoard2);
        trelloBoards.add(trelloBoard3);
        //When
        List<TrelloBoardDto> mappedTrelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertEquals("name2", mappedTrelloBoardsDto.get(1).getName());
        assertEquals(3, mappedTrelloBoardsDto.size());
    }

    @Test
    public void testMapToList() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("id1", "name1", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("id2", "name2", true);
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(trelloListDto1);
        trelloListsDto.add(trelloListDto2);
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);
        //Then
        assertEquals("id1", trelloLists.get(0).getId());
        assertEquals(2, trelloLists.size());
    }

    @Test
    public void testMapToListDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("id1", "name1", true);
        TrelloList trelloList2 = new TrelloList("id2", "name2", true);
        TrelloList trelloList3 = new TrelloList("id3", "name3", true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);
        trelloLists.add(trelloList3);
        //When
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);
        //Then
        assertEquals("id1", trelloListsDto.get(0).getId());
        assertEquals(3, trelloListsDto.size());
    }

    @Test
    public void testMapToTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "description", "pos", "id");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals(trelloCard.getDescription(), trelloCardDto.getDescription());
        assertEquals(trelloCard.getListId(), trelloCardDto.getListId());
        assertEquals(trelloCard.getName(), trelloCardDto.getName());
        assertEquals(trelloCard.getPos(), trelloCardDto.getPos());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("name", "description", "pos", "id");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals(trelloCard.getDescription(), trelloCardDto.getDescription());
        assertEquals(trelloCard.getListId(), trelloCardDto.getListId());
        assertEquals(trelloCard.getName(), trelloCardDto.getName());
        assertEquals(trelloCard.getPos(), trelloCardDto.getPos());
    }
}

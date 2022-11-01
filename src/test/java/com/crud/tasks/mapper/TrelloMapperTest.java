package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrelloMapperTest {

    TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void shouldMapToBoards(){
        //Given
        List<TrelloBoardDto> trelloBoardDtos = List.of(
                new TrelloBoardDto("id", "name",
                        List.of(new TrelloListDto("id", "name", true)))
        );
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);
        //Then
        TrelloBoard trelloBoard = trelloBoards.get(0);
        TrelloBoardDto trelloBoardDto = trelloBoardDtos.get(0);
        TrelloList trelloList = trelloBoard.getLists().get(0);
        TrelloListDto trelloListDto = trelloBoardDto.getLists().get(0);

        assertEquals(trelloBoard.getId(), trelloBoardDto.getId());
        assertEquals(trelloBoard.getName(), trelloBoardDto.getName());
        assertEquals(trelloList.getId(), trelloListDto.getId());
        assertEquals(trelloList.getName(), trelloListDto.getName());
        assertEquals(trelloList.isClosed(), trelloListDto.isClosed());
    }

    @Test
    public void shouldMapToBoardsDto(){
        //Given
        List<TrelloBoard> trelloBoards = List.of(
                new TrelloBoard("id", "name",
                        List.of(new TrelloList("id", "name", true)))
        );
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        TrelloBoard trelloBoard = trelloBoards.get(0);
        TrelloBoardDto trelloBoardDto = trelloBoardDtos.get(0);
        TrelloList trelloList = trelloBoard.getLists().get(0);
        TrelloListDto trelloListDto = trelloBoardDto.getLists().get(0);

        assertEquals(trelloBoard.getId(), trelloBoardDto.getId());
        assertEquals(trelloBoard.getName(), trelloBoardDto.getName());
        assertEquals(trelloList.getId(), trelloListDto.getId());
        assertEquals(trelloList.getName(), trelloListDto.getName());
        assertEquals(trelloList.isClosed(), trelloListDto.isClosed());
    }

    @Test
    public void shouldMapToList(){
        //Given
        List<TrelloListDto> trelloListDtos = List.of(new TrelloListDto("id","name",true));
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);
        //Then
        TrelloList trelloList = trelloLists.get(0);
        TrelloListDto trelloListDto = trelloListDtos.get(0);

        assertEquals(trelloList.getId(), trelloListDto.getId());
        assertEquals(trelloList.getName(), trelloListDto.getName());
        assertEquals(trelloList.isClosed(), trelloListDto.isClosed());
    }

    @Test
    public void shouldMapToListDto(){
        //Given
        List<TrelloList> trelloLists = List.of(new TrelloList("id", "name", true));
        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);
        //Then
        TrelloList trelloList = trelloLists.get(0);
        TrelloListDto trelloListDto = trelloListDtos.get(0);

        assertEquals(trelloList.getId(), trelloListDto.getId());
        assertEquals(trelloList.getName(), trelloListDto.getName());
        assertEquals(trelloList.isClosed(), trelloListDto.isClosed());
    }

    @Test
    public void shouldMapToCardDto(){
        //Given
        TrelloCard trelloCard = new TrelloCard("name", "description", "pos", "listId");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals(trelloCard.getName(),trelloCardDto.getName());
        assertEquals(trelloCard.getDescription(),trelloCardDto.getDescription());
        assertEquals(trelloCard.getPos(),trelloCardDto.getPos());
        assertEquals(trelloCard.getListId(),trelloCardDto.getListId());
    }

    @Test
    public void shouldMapToCard(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "description", "pos", "listId");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals(trelloCard.getName(),trelloCardDto.getName());
        assertEquals(trelloCard.getDescription(),trelloCardDto.getDescription());
        assertEquals(trelloCard.getPos(),trelloCardDto.getPos());
        assertEquals(trelloCard.getListId(),trelloCardDto.getListId());
    }
}

package com.crud.tasks.service;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TrelloServiceTest {
    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Test
    public void shouldFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(new TrelloListDto("1", "Test List", false));

        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(new TrelloBoardDto("1", "Test Board", trelloListDtoList));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoList);

        //When
        List<TrelloBoardDto> fetchedBoards = trelloService.fetchTrelloBoards();

        //Then
        assertNotNull(fetchedBoards);
        assertEquals("1", fetchedBoards.get(0).getId());
        assertEquals("Test Board", fetchedBoards.get(0).getName());
        assertEquals(trelloListDtoList, fetchedBoards.get(0).getLists());
    }

    @Test
    public void shouldCreateNullTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "description", "pos", "1");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(null);
        //When
        Optional<CreatedTrelloCardDto> createdCard = Optional.ofNullable(trelloService.createTrelloCard(trelloCardDto));
        //Then
        assertTrue(createdCard.isEmpty());
    }
}

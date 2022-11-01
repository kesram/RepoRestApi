package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.assertj.core.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
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
    void shouldFetchEmptyList() {
        // Given
        List<TrelloListDto> trelloLists =
                List.of(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> trelloBoards =
                List.of(new TrelloBoardDto("1", "test", trelloLists));

        List<TrelloList> mappedTrelloLists =
                List.of(new TrelloList("1", "test_list", false));

        List<TrelloBoard> mappedTrelloBoards =
                List.of(new TrelloBoard("1", "test", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(List.of());
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(List.of());

        // When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        // Then
        Assertions.assertThat(trelloBoardDtos).isNotNull();
        Assertions.assertThat(trelloBoardDtos.size()).isEqualTo(0);
    }

    @Test
    void shouldFetchTrelloBoards() {
        // Given
        List<TrelloListDto> trelloLists =
                List.of(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> trelloBoards =
                List.of(new TrelloBoardDto("1", "test", trelloLists));

        List<TrelloList> mappedTrelloLists =
                List.of(new TrelloList("1", "test_list", false));

        List<TrelloBoard> mappedTrelloBoards =
                List.of(new TrelloBoard("1", "test", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoards);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);

        // When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        // Then
        Assertions.assertThat(trelloBoardDtos).isNotNull();
        Assertions.assertThat(trelloBoardDtos.size()).isEqualTo(1);

        trelloBoardDtos.forEach(trelloBoardDto -> {

            Assertions.assertThat(trelloBoardDto.getId()).isEqualTo("1");
            Assertions.assertThat(trelloBoardDto.getName()).isEqualTo("test");

            trelloBoardDto.getLists().forEach(trelloListDto -> {
                Assertions.assertThat(trelloListDto.getId()).isEqualTo("1");
                Assertions.assertThat(trelloListDto.getName()).isEqualTo("test_list");
                Assertions.assertThat(trelloListDto.isClosed()).isFalse();
            });
        });
    }

    @Test
    public void shouldCreateCard(){

        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name","desc","pos","1");
        TrelloCard trelloCard = new TrelloCard("name","desc","pos","1");
        TrelloDto trelloDto = new TrelloDto(1,1);
        AttachmentsByTypeDto attachmentsByTypeDto = new AttachmentsByTypeDto(trelloDto);
        BadgesDto badgesDto = new BadgesDto(1,attachmentsByTypeDto);
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1","nameCard","shortUrl",badgesDto);

        when(trelloMapper.mapToCard(trelloCardDto)).thenReturn(trelloCard);
        when(trelloMapper.mapToCardDto(trelloCard)).thenReturn(trelloCardDto);
        when(trelloService.createTrelloCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        //When
        CreatedTrelloCardDto createdTrelloCardDto1 = trelloFacade.createCard(trelloCardDto);
        //Then
        assertThat(createdTrelloCardDto1.getId()).isEqualTo("1");
        assertThat(createdTrelloCardDto1.getName()).isEqualTo("nameCard");
        assertThat(createdTrelloCardDto1.getShortUrl()).isEqualTo("shortUrl");
        assertThat(createdTrelloCardDto1.getBadges().getVotes()).isEqualTo(1);
        assertThat(createdTrelloCardDto1.getBadges().getAttachments().getTrello().getBoard()).isEqualTo(1);
        assertThat(createdTrelloCardDto1.getBadges().getAttachments().getTrello().getCard()).isEqualTo(1);
        verify(trelloValidator).validateCard(trelloCard);
    }
}

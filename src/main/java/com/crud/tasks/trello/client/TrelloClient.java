package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TrelloClient {

    private final RestTemplate restTemplate;

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloToken;
    @Value("${trello.app.username}")
    private String username;

    public List<TrelloBoardDto> getTrelloBoards() {

        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(URLbilder(), TrelloBoardDto[].class);

        return Optional.ofNullable(boardsResponse)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    private URI URLbilder(){

        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + username + "/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id")
                .queryParam("lists","all")
                .build()
                .encode()
                .toUri();
    }

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/cards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getListId())
                .build()
                .encode()
                .toUri();

        return restTemplate.postForObject(url, null, CreatedTrelloCard.class);
    }


}






//package com.crud.tasks.trello.client;
//
//import com.crud.tasks.domain.TrelloBoardDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.net.URI;
//import java.util.*;
//
//@Component
//@RequiredArgsConstructor
//public class TrelloClient {
//
//    private final RestTemplate restTemplate;
//
//    @Value("${trello.api.endpoint.prod}")
//    private String trelloApiEndpoint;
//    @Value("${trello.app.key}")
//    private String trelloAppKey;
//    @Value("${trello.app.token}")
//    private String trelloToken;
//
//    public List<TrelloBoardDto> getTrelloBoards(){
//        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/marcinsek123/boards")
//                .queryParam("key",trelloAppKey)
//                .queryParam("token", trelloToken)
//                .queryParam("fields", "name,id")
//                .build()
//                .encode()
//                .toUri();
//
//        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
//
//        //lub za pomocą Optional
//        return Optional.ofNullable(boardsResponse)
//                .map(Arrays::asList)
//                .orElse(Collections.emptyList());
//        //Za pomocą If
////        if(boardsResponse != null) {
////            return Arrays.asList(boardsResponse);
////        }
////        return new ArrayList<>();
////        return Collections.emptyList();
//
//
//
//
//    }
//
//
////    public List<TrelloBoardDto> getTrelloBoards() {
////        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(
////                trelloApiEndpoint + "/members/marcinsek123/boards" +
////                        "?key=" + trelloAppKey + "?token=" + trelloToken,
////                TrelloBoardDto[].class
//      (api.trello.com/1/members/marcinsek123/boards?key=6360c572a76abfea7865faffd26c1634&token=c2e033af48d9ab90620b35159303a52f82836edbe9d52ae3295c47f1d8d45608&fields=name,id, TrelloBoardDto[].class);
////        );
////    }
//
//}

package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/trello")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TrelloController {

    private final TrelloFacade trelloFacade;

    @PostMapping("cards")
    public ResponseEntity<CreatedTrelloCardDto> createTrelloCard (@RequestBody TrelloCardDto trelloCardDto){
        return ResponseEntity.ok(trelloFacade.createCard(trelloCardDto));
    }

    @GetMapping("boards")
    public ResponseEntity<List<TrelloBoardDto>> getTrelloBoards(){
        return ResponseEntity.ok(trelloFacade.fetchTrelloBoards());
    }
}

//            trelloBoards.stream()
//                .filter(trelloBoardDto -> trelloBoardDto.getId() != null && trelloBoardDto.getName() != null)
//                .filter(trelloBoardDto -> trelloBoardDto.getName().contains("Kodill"))
////                .filter(trelloBoardDto -> Objects.equals(trelloBoardDto.getName(), "Kodill Application"))
//                .forEach(trelloBoardDto -> {
//                            System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName());
//                });

//        trelloBoards.forEach(trelloBoardDto -> {
//            System.out.println(trelloBoardDto.getId() + " - " + trelloBoardDto.getName());
//            System.out.println("This board contains lists: ");
//            trelloBoardDto.getLists().forEach(trelloList -> {
//                System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed());
//            });
//        });


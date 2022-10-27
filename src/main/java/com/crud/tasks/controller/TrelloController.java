package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("v1/trello")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TrelloController {

    private final TrelloClient trelloClient;

    @PostMapping("cards")
    public ResponseEntity<CreatedTrelloCard> createTrelloCard (@RequestBody TrelloCardDto trelloCardDto){
        return ResponseEntity.ok(trelloClient.createNewCard(trelloCardDto));
    }

    @GetMapping("boards")
    public ResponseEntity<List<TrelloBoardDto>> getTrelloBoards(){
        return ResponseEntity.ok(trelloClient.getTrelloBoards());

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
        }
}

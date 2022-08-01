package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TrelloMapperTest {

    private TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void mapperTrelloTest() {
        // Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("1","Testowa lista",false));
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1","Testowa lista", false));
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(new TrelloBoardDto("test_id", "Kodilla", trelloListsDto));
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("Kodilla","Kodilla", trelloLists));
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test task",
                "Test Description",
                "top",
                "test_id"
        );
        TrelloCard trelloCard = new TrelloCard(
                "Test task",
                "Test Description",
                "top",
                "test_id"
        );
        // When
        List<TrelloList> mapTrelloLists = trelloMapper.mapToList(trelloListsDto);
        List<TrelloListDto> mapTrelloListsDto = trelloMapper.mapToListDto(trelloLists);
        List<TrelloBoard> mapTrelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);
        List<TrelloBoardDto> mapTrelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);
        TrelloCard mapTrelloCard = trelloMapper.mapToCard(trelloCardDto);
        TrelloCardDto mapTrelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals(trelloListsDto.get(0).getId(),mapTrelloLists.get(0).getId());
        assertEquals(trelloListsDto.get(0).getName(),mapTrelloLists.get(0).getName());
        assertEquals(trelloLists.get(0).getId(),mapTrelloListsDto.get(0).getId());
        assertEquals(trelloLists.get(0).getName(),mapTrelloListsDto.get(0).getName());
        assertEquals(trelloBoardsDto.get(0).getId(),mapTrelloBoards.get(0).getId());
        assertEquals(trelloBoardsDto.get(0).getName(),mapTrelloBoards.get(0).getName());
        assertEquals(trelloBoards.get(0).getId(),mapTrelloBoardsDto.get(0).getId());
        assertEquals(trelloBoards.get(0).getName(),mapTrelloBoardsDto.get(0).getName());
        assertEquals(trelloCardDto.getName(),mapTrelloCard.getName());
        assertEquals(trelloCardDto.getDescription(),mapTrelloCard.getDescription());
        assertEquals(trelloCardDto.getPos(),mapTrelloCard.getPos());
        assertEquals(trelloCardDto.getListId(),mapTrelloCard.getListId());
        assertEquals(trelloCard.getName(),mapTrelloCardDto.getName());
        assertEquals(trelloCard.getDescription(),mapTrelloCardDto.getDescription());
        assertEquals(trelloCard.getPos(),mapTrelloCardDto.getPos());
        assertEquals(trelloCard.getListId(),mapTrelloCardDto.getListId());
    }
}

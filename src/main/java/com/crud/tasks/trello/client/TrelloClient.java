package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.cards.CreatedTrelloCard;
import com.crud.tasks.domain.cards.CreatedTrelloCard223;
import com.crud.tasks.domain.cards.TrelloCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient {

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.app.username}")
    private String trelloUsername;

    @Autowired
    private RestTemplate restTemplate;

    public List<TrelloBoardDto> getTrelloBoards() {
        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(prepareUrl(), TrelloBoardDto[].class);

        return Arrays.asList(Optional.ofNullable(boardsResponse).orElse(new TrelloBoardDto[0]));

//        TrelloBoardDto[] boardsResponse = restTemplate.getForObject("https://api.trello.com/1/members/sebastianinglot/boards?key=4a3548f428e3b44267d1006be08182e0&token=bce1d0c620988cdecbebc930fc90b01b48f5f0ad81ff4f000532f0fa958d0a3a&fields=name,id", TrelloBoardDto[].class);

//        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(
//                trelloApiEndpoint + "/members/sebastianinglot/boards"
//                        + "?key=" + trelloAppKey + "&token=" + trelloToken, TrelloBoardDto[].class);
    }

    private URI prepareUrl() {
        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + trelloUsername + "/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
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

    public CreatedTrelloCard223 createNewCard223(TrelloCardDto trelloCardDto) {

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

        return restTemplate.postForObject(url, null, CreatedTrelloCard223.class);
    }
}

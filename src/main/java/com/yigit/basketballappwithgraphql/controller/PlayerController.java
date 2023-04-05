package com.yigit.basketballappwithgraphql.controller;

import com.yigit.basketballappwithgraphql.dto.PlayerRequest;
import com.yigit.basketballappwithgraphql.entity.Player;
import com.yigit.basketballappwithgraphql.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PlayerController {

    private final PlayerRepository playerRepository;

    @QueryMapping
    Iterable<Player> players() {
        log.info("players listed");
        return playerRepository.findAll();
    }

    @MutationMapping
    Player addPlayer(@Argument PlayerRequest playerRequest) {
        Player player = new Player(null, playerRequest.getName(), playerRequest.getSurname(), playerRequest.getPosition());
        playerRepository.save(player);
        log.info("player "+player.getId()+" created");
        return player;
    }

}

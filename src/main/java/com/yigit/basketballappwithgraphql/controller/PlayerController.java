package com.yigit.basketballappwithgraphql.controller;

import com.yigit.basketballappwithgraphql.dto.PlayerRequest;
import com.yigit.basketballappwithgraphql.entity.Player;
import com.yigit.basketballappwithgraphql.service.PlayerService;
import com.yigit.basketballappwithgraphql.util.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @QueryMapping
    Iterable<Player> players(@Argument Integer page, @Argument Integer size, Principal principal) {
        PageRequest pageRequest = page == null && size == null ? PageRequest.of(0,Integer.MAX_VALUE):PageRequest.of(page,size);
        return playerService.players(pageRequest,principal);
    }

    @MutationMapping
    Player addPlayer(@Argument PlayerRequest playerRequest, Principal principal ) throws CustomException {
        return playerService.addPlayer(playerRequest,principal);
    }

    @MutationMapping
    Player deletePlayer(@Argument Long id, Principal principal) throws CustomException {
        return playerService.deletePlayer(id,principal);
    }

}

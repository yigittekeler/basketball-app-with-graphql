package com.yigit.basketballappwithgraphql.service;

import com.yigit.basketballappwithgraphql.dto.PlayerRequest;
import com.yigit.basketballappwithgraphql.entity.Player;
import com.yigit.basketballappwithgraphql.util.CustomException;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;

import java.security.Principal;


public interface PlayerService {

    Iterable<Player> players(PageRequest pageRequest,Principal principal );

    Player addPlayer(@Argument PlayerRequest playerRequest, Principal principal ) throws CustomException;

    Player deletePlayer(@Argument Long id, Principal principal) throws CustomException;
}

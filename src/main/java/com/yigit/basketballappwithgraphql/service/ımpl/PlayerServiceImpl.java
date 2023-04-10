package com.yigit.basketballappwithgraphql.service.ımpl;

import com.yigit.basketballappwithgraphql.dto.PlayerRequest;
import com.yigit.basketballappwithgraphql.entity.Player;
import com.yigit.basketballappwithgraphql.repository.PlayerPagingAndSortingRepository;
import com.yigit.basketballappwithgraphql.repository.PlayerRepository;
import com.yigit.basketballappwithgraphql.service.PlayerService;
import com.yigit.basketballappwithgraphql.util.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerPagingAndSortingRepository pagingRepository;

    private static final int MAX_TEAM_SIZE = 12;

    @Override
    public Iterable<Player> players(PageRequest pageRequest, Principal principal ) {
        return pagingRepository.findAll(pageRequest);
    }

    @Override
    public Player addPlayer(PlayerRequest playerRequest,Principal principal ) throws CustomException {
        Player player = new Player(null, playerRequest.getName(), playerRequest.getSurname(), playerRequest.getPosition());
        if(getTeamSize() >= MAX_TEAM_SIZE) {
            throw new CustomException(HttpStatus.BAD_REQUEST,"you have reached the max team size");
        }
        playerRepository.save(player);
        log.info("player "+player.getId()+" created by " + principal.getName() +" at " + Instant.now() );
        return player;
    }

    @Override
    public Player deletePlayer(Long id, Principal principal ) throws CustomException {
        Player player = playerRepository.findById(id).orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST,"no players found for given id"));
        playerRepository.delete(player);
        log.info("player "+player.getId()+" deleted by " + principal.getName() +" at " + Instant.now());
        return player;
    }

    private long getTeamSize() {
        return playerRepository.count();
    }
}

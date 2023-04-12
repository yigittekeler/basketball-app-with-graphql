package com.yigit.basketballappwithgraphql.player;

import com.yigit.basketballappwithgraphql.dto.PlayerRequest;
import com.yigit.basketballappwithgraphql.entity.Player;
import com.yigit.basketballappwithgraphql.enums.Position;
import com.yigit.basketballappwithgraphql.repository.PlayerPagingAndSortingRepository;
import com.yigit.basketballappwithgraphql.repository.PlayerRepository;
import com.yigit.basketballappwithgraphql.service.ımpl.PlayerServiceImpl;
import com.yigit.basketballappwithgraphql.util.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private PlayerPagingAndSortingRepository pagingRepository;

    @Mock
    private Principal principal;

    @InjectMocks
    private PlayerServiceImpl playerService;

    private static final int MAX_TEAM_SIZE = 12;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPlayers() {

        PageRequest pageRequest = PageRequest.of(0, 10);
        List<Player> players = new ArrayList<>();
        players.add(new Player(2L, "alperen", "sengun", Position.PF));
        players.add(new Player(3L, "furkan", "korkmaz", Position.C));
        players.add(new Player(4L, "cedi", "osman", Position.SF));
        Page<Player> page = new PageImpl<>(players);
        when(pagingRepository.findAll(pageRequest)).thenReturn(page);

        Iterable<Player> result = playerService.players(pageRequest, principal);
        assertEquals(page, result);

        verify(pagingRepository, times(1)).findAll(pageRequest);
    }

    @Test
    void testAddPlayer() throws CustomException {
        PlayerRequest playerRequest = new PlayerRequest("ahmet", "korkmaz", Position.PG);
        Player player = new Player(null, "ahmet", "korkmaz", Position.PG);
        when(playerRepository.count()).thenReturn((long) (MAX_TEAM_SIZE - 1));
        when(playerRepository.save(any(Player.class))).thenReturn(player);

        Player result = playerService.addPlayer(playerRequest, principal);

        assertEquals(player, result);
        verify(playerRepository, times(1)).save(any(Player.class));
    }

    @Test
    void testAddPlayer_MaxTeamSizeReached() {
        PlayerRequest playerRequest = new PlayerRequest("hatalı", "hatalı", Position.PG);
        when(playerRepository.count()).thenReturn((long) MAX_TEAM_SIZE);
        assertThrows(CustomException.class, () -> playerService.addPlayer(playerRequest, principal));
    }

    @Test
    void testDeletePlayer() throws CustomException {
        Player player = new Player(1L, "John", "Doe", Position.PG);
        when(playerRepository.findById(1L)).thenReturn(java.util.Optional.of(player));

        Player result = playerService.deletePlayer(1L, principal);

        assertEquals(player, result);
        verify(playerRepository, times(1)).delete(player);
    }

}
package com.yigit.basketballappwithgraphql;

import com.yigit.basketballappwithgraphql.entity.Player;
import com.yigit.basketballappwithgraphql.repository.PlayerRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BasketballAppWithGraphqlApplication {

    @Autowired
    private PlayerRepository playerRepository;

    public static void main(String[] args) {
        SpringApplication.run(BasketballAppWithGraphqlApplication.class, args);
    }
//    @PostConstruct
//    public void init(){
//        Player player = new Player();
//        player.setName("alperen");
//        player.setSurname("sengun");
//        player.setPosition("central_forward");
//        playerRepository.save(player);
//
//        Player player2 = new Player();
//        player2.setName("cedi");
//        player2.setSurname("osman");
//        player2.setPosition("center");
//        playerRepository.save(player2);
//
//    }

}


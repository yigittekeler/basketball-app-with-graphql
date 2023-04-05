package com.yigit.basketballappwithgraphql.repository;

import com.yigit.basketballappwithgraphql.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {

}

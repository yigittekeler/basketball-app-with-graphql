package com.yigit.basketballappwithgraphql.repository;

import com.yigit.basketballappwithgraphql.entity.Player;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PlayerPagingAndSortingRepository extends PagingAndSortingRepository<Player,Long> {
}

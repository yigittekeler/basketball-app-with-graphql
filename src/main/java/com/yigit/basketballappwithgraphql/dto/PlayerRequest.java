package com.yigit.basketballappwithgraphql.dto;

import com.yigit.basketballappwithgraphql.enums.Position;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerRequest {

    private String name;
    private String surname;
    private Position position;
}

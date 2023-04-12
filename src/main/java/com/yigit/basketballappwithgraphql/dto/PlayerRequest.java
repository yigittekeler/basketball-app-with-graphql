package com.yigit.basketballappwithgraphql.dto;

import com.yigit.basketballappwithgraphql.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRequest {

    private String name;
    private String surname;
    private Position position;
}

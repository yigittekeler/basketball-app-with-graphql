package com.yigit.basketballappwithgraphql.util;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;


@Getter
@Setter
@Builder
public class CustomResponseEntity<T> implements Serializable {
    T responseEntity;
    HttpStatus statusCode;

    public static <T> CustomResponseEntity<T> success(T model){
        return CustomResponseEntity.<T>builder()
                .statusCode(HttpStatus.OK)
                .responseEntity(model)
                .build();

    }
}
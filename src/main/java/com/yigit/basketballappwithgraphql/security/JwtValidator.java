package com.yigit.basketballappwithgraphql.security;
import com.yigit.basketballappwithgraphql.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {


    private String secret = "cozef";

    public User validate(String token) {

        User jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new User();

            jwtUser.setUsername(body.getSubject());
            jwtUser.setPassword((String) body.get("userId"));
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return jwtUser;
    }
}
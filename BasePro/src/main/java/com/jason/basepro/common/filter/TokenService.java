package com.jason.basepro.common.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jason.basepro.base.entity.User;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class TokenService {
    private static final long EXPIRE_TIME = 8 * 60 * 60 * 1000;

    private static final String KEY = "jwt2023";

    public String createToken(User user) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        String token="";
        token= JWT.create().withAudience(user.getId().toString()) // 将 user id 保存到 token 里面
                .withIssuer("Jason")
                .withSubject("grant token")
                .withAudience("web")
                .withExpiresAt(date) //五分钟后token过期
                .sign(Algorithm.HMAC256(KEY)); // 以 password 作为 token 的密钥
        return token;
    }

    public boolean verifyToken(String token){
        boolean isValid = true;
        try {
            Algorithm algorithm = Algorithm.HMAC256(KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("Jason").build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if(new Date().compareTo(decodedJWT.getExpiresAt()) > 0){//token过期
                isValid = false;
            }
        }catch (JWTVerificationException e){//无效token
            log.error("无效的token：{}",token);
            isValid = false;
        }
        return isValid;
    }
}

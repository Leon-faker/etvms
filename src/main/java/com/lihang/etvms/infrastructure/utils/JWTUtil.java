package com.lihang.etvms.infrastructure.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * JWT 工具类
 *
 * @date 2022/12/8
 **/
public class JWTUtil {

    public static final Long EXPIRED_TIME = 2L * 60;
    private static final Key KEY = Keys.hmacShaKeyFor("i-/WSs0PvzR4\"*:TuQ28fC#7~+)%3|pU".getBytes(StandardCharsets.UTF_8));

    private JWTUtil() {

    }

    /**
     * 根据用户信息生成Token
     *
     * @param id 用户ID
     * @return token
     */
    public static String encode(Long id) {
        Date expired = Date.from(LocalDateTime.now().plusMinutes(EXPIRED_TIME).atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setId(id.toString())
                .setExpiration(expired)
                .signWith(KEY)
                .compact();
    }

    /**
     * 根据Token解析用户信息
     *
     * @param token token
     * @return 用户ID
     */
    public static Long decode(String token) {
        if (null == token || "".equals(token)) {
            return null;
        }
        token = token.trim();
        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .setSigningKey(KEY)
                    .build()
                    .parseClaimsJws(token);
            return Long.parseLong(jws.getBody().getId());
        } catch (Exception e) {
            return -1L;
        }
    }
}

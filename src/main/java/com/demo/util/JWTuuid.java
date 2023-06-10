package com.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.UUID;

public class JWTuuid {
    private static final long time=60*60*24*1000;
    private static final String sginature="weifeng0000000";
    public  static  final  String creatjwt(String name, Object user){
        JwtBuilder jwtBuilder= Jwts.builder();
        String jwttoken=jwtBuilder
                //header 头
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")//加密算法

                //payload  --载荷
                .claim("username",name)
                .claim("user",user)

                //主题
                .setSubject("weifeng-test")
                //有效时间
                .setExpiration(new Date(System.currentTimeMillis()+time))
                //jwt id
                .setId(UUID.randomUUID().toString())
                //签名
                .signWith(SignatureAlgorithm.HS256,sginature)
                .compact();
        return jwttoken;
    }

    //解析 JWT
    public static Claims testJwt(String jwt){
        try {
            Claims claims=Jwts.parser()
                    .setSigningKey(sginature)//放密钥
                    .parseClaimsJws(jwt)//放入需要解析的jwt串
                    .getBody();
            return claims;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}

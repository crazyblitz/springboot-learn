package com.ley.crazyblitz.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhiyuan
 **/
@Component
@Slf4j
public class JwtTokenUtils implements Serializable {


    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    private static final long serialVersionUID = 5021913450632406869L;

    public String getUsernameFromToken(String token, String secret) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token, secret);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public Date getCreatedDateFromToken(String token, String secret) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token, secret);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String token, String secret) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token, secret);
            expiration = claims.getExpiration();
            log.info("jwt解密:{}", claims);
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    private Claims getClaimsFromToken(String token, String secret) {
        SecretKey secretKey = generalKey(secret);
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.debug("validate is token error ", e);
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate(Long expiration) {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    private Boolean isTokenExpired(String token, String secret) {
        final Date expiration = getExpirationDateFromToken(token, secret);
        return expiration.before(new Date());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    public String generateToken(String userName, String secret, Long expiration) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put(CLAIM_KEY_USERNAME, userName);
        claims.put(CLAIM_KEY_CREATED, new Date(System.currentTimeMillis()));
        return generateToken(claims, secret, expiration);
    }

    private String generateToken(Map<String, Object> claims, String secret, Long expiration) {
        //生成签名的时候使用的秘钥secret
        SecretKey secretKey = generalKey(secret);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .setExpiration(generateExpirationDate(expiration))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset, String secret) {
        final Date created = getCreatedDateFromToken(token, secret);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && !isTokenExpired(token, secret);
    }

    public String refreshToken(String token, String secret, Long expiration) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token, secret);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims, secret, expiration);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token, String userName, String secret) {
        final String username = getUsernameFromToken(token, secret);
        return (
                username.equals(userName)
                        && !isTokenExpired(token, secret));
    }


    private SecretKey generalKey(String secret) {
        // 使用base64解码
        byte[] encodedKey = Base64.decodeBase64(secret);
        // 根据给定的字节数组使用AES加密算法构造一个密钥
        SecretKey secretKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return secretKey;
    }
}

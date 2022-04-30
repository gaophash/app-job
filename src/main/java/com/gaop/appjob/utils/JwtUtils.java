package com.gaop.appjob.utils;

import com.gaop.appjob.entity.SecurityUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Map;

/**
 * @version 1.0.0
 * @author: gaopeng
 * @date: 2022/4/26 11:07
 * @description: JwtUtils
 */
public class JwtUtils {
    /**
     * token过期时间   24小时
     */
    public static final long EXPIRE = 1000 * 60 * 60 * 24;
    /**
     * 密钥
     */
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";


        public  static  String createToken(String userName){
            return Jwts.builder()
                    .setSubject(userName)
                    //生成时间
                    .setIssuedAt(new Date())
                    //过期时间
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                    .signWith(SignatureAlgorithm.HS512, APP_SECRET)
                    .compact();
        }

        /**
         * 从数据声明生成令牌
         *
         * @param claims 数据声明
         * @return 令牌
         */
        private static String generateToken(Map<String, Object> claims, Long expiration) {
            Date expirationDate = new Date(System.currentTimeMillis() + expiration);
            return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, APP_SECRET).compact();
        }


        /**
         * 从token中解析出数据
         * @param token 令牌
         * @return
         */
        private static Claims getClaimsFromToken(String token) {
            Claims claims;
            try {
                claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
            } catch (Exception e) {
                e.printStackTrace();
                claims = null;
            }
            return claims;
        }

        /**
         * 从令牌中获取用户名
         * @param token 令牌
         * @return 用户名
         */
        public static String getUsernameFromToken(String token) {
            String username;
            try {
                Claims claims = getClaimsFromToken(token);
                if (null == claims) {
                    return "";
                }
                username = claims.getSubject();
            } catch (Exception e) {
                e.printStackTrace();
                username = null;
            }
            return username;
        }

        /**
         * 判断令牌是否过期
         *
         * @param token 令牌
         * @return 是否过期
         */
        public static Boolean isTokenExpired(String token) {
            try {
                Claims claims = getClaimsFromToken(token);
                Date expiration = claims.getExpiration();
                return expiration.before(new Date());
            } catch (Exception e) {
                return true;
            }
        }

        /**
         * 刷新令牌
         *
         * @param token 原令牌
         * @return 新令牌
         */
        public static String refreshToken(String token) {
            String refreshedToken;
            try {
                Claims claims = getClaimsFromToken(token);
                claims.put(Claims.ISSUED_AT, new Date());
                refreshedToken = generateToken(claims,2*EXPIRE);
            } catch (Exception e) {
                refreshedToken = null;
            }
            return refreshedToken;
        }

        /**
         * 验证令牌
         *
         * @param token       令牌
         * @param userDetails 用户
         * @return 是否有效
         */
        public static Boolean validateToken(String token, UserDetails userDetails) {
            SecurityUser user = (SecurityUser) userDetails;
            String username = getUsernameFromToken(token);
            return (username.equals(user.getUsername()) && !isTokenExpired(token));
        }

}

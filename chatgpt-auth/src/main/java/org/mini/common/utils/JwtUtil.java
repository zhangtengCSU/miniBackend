package org.mini.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.mini.common.http.ResponseEnum;
import org.mini.common.exceptions.GptException;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT tools
 */
@Slf4j
public class JwtUtil {

    // Token key
    private static final String SECRET = "cFTkAoAXdPY!6#&@qHpxBXqs8g4mX&OB";

    /**
     * create jwt token
     */
    public static String createToken(String claim, String id) {
        // Create a Token generator
        JWTCreator.Builder builder = JWT.create();
        Map<String, Object> header = new HashMap<>(2);
        header.put("Type", "Jwt");
        header.put("alg", "HS256");
        // Fill header
        builder.withHeader(header);
        // Fill payload
        builder.withClaim(claim, id);
        // Expired in 60 minutes
        Date date = new Date(System.currentTimeMillis() + 60 * 60 * 1000L * 2);
        builder.withExpiresAt(date);
        // Build a token
        return builder.sign(Algorithm.HMAC256(SECRET));
    }

    public static Boolean verifyToken(String token) {
        try {
            DecodedJWT verify = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
            return verify != null;
        }
        // token expired & JWTDecodeException
        catch (TokenExpiredException e) {
            throw GptException.build(ResponseEnum.TOKEN_EXPIRED);
        } catch (JWTDecodeException ex) {
            return false;
        }
        // other exception
        catch (Exception ex) {
            // verify false, not do anything
        }
        return false;
    }

}

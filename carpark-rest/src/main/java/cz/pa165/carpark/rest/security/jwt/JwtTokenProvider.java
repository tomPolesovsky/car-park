package cz.pa165.carpark.rest.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * JWT token provider
 */
@Component
public class JwtTokenProvider {

    private String secretKey = "TD5eGajyi7=";

    /**
     * Creates new token according to username
     *
     * @param username input username
     * @return new token or null
     */
    public String createToken(String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.create()
                    .withClaim("username", username)
                    .withClaim("created", new Date())
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Resolve token based on http request
     *
     * @param req input request
     * @return token or null
     */
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }

        return null;
    }

    /**
     * Parse JWT token and return username
     *
     * @param token input token
     * @return username or null
     */
    private String parseToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);

            return jwt.getClaim("username").toString();
        } catch (UnsupportedEncodingException | JWTVerificationException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * Validate JWT token
     *
     * @param token input token
     * @return true or false
     */
    public boolean validateToken(String token) {
        String username = this.parseToken(token);
        return username != null;
    }

}
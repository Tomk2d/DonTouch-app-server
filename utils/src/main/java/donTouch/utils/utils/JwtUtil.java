package donTouch.utils.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

//@Component
public class JwtUtil {
//    @Value("${jwt.secret}")
    private static String secret = "test";

    public static Long extractId(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            return Long.parseLong(claims.getSubject());
        } catch (SignatureException e) {
            throw new IllegalArgumentException("Invalid token");
        }
    }
}

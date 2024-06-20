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
    private static String secret = "RFbMT_0NteLX4qyt1PLwM40xMRKA0fEqSQcey0R7qOKOzXw0dfJV7__-1PxS2c-OS4AYluDkx8TIvZXfvaRyOw";
////
////    @PostConstruct
////    public void init() {
////        System.out.println("생성되니 친구야?");
////        staticSecret = this.secret;
////    }
//
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

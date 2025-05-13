package pe.dinnra_web.sistema_gestion.api.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JavaWebTokenUtil {

    private static final String SECRET_KEY = "bXlTdXBlckVuY3J5cHRlZFJhbmRvbUtleVRoYXRJc1ZlcnlMb25n";

    public String generateToken (String username, String position){
        return Jwts.builder()
                .setSubject(username)
                .claim("posicion", position)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + + 86400000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public String extractPosition(String token) {
        return extractClaims(token).get("position", String.class);
    }

    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token)) && !extractClaims(token).getExpiration().before(new Date());
    }
}

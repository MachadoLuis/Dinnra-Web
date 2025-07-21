package pe.dinnra_web.sistema_gestion.api.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pe.dinnra_web.sistema_gestion.api.model.entity.User;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtUtil {

    @Value("${spring.security.jwt.secret-key}")
    private String SECRET_KEY;


    public String generateToken (String idUser, String username, String position, Integer expiration){

        if (idUser == null || username == null || position == null){
            throw new IllegalArgumentException("Token no creado por datos en Null");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("position", position);

        return Jwts.builder()
                //A quien o que pertenecera el Token
                .subject(idUser)
                //Datos extra adicionales
                .claims(claims)
                //Fecha en la que se crea el Token
                .issuedAt(new Date(System.currentTimeMillis()))
                //Fecha en la que el token debe expirar
                .expiration(new Date(System.currentTimeMillis() + expiration))
                //Firma del token con una clave secreta que usa un algoritmo para ser creada
                .signWith(key())
                //Genera el token y lo devuelve como un string
                .compact();

    }

    public String generateRefreshToken(String idUser, String username, String position){
        Integer expirationRefreshToken = 432000000;
        return generateToken(idUser, username, position, expirationRefreshToken);
    }

    public String generateTokenFromRefreshToken (String refreshToken){
        Integer expirationToken = /*1 minuto*/ 60000;

        String idUser = extractIdUser(refreshToken);
        String username = extractUsername(refreshToken);
        String position = extractPosition(refreshToken);

        return generateToken(idUser, username, position, expirationToken);
    }

    public SecretKey key (){
        byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }

    public boolean validateToken(String token, User user) {
        //String username = extractUsername(token);
        String subject = extractIdUser(token);
        String idUser = String.valueOf(user.getIdUser());
        return (idUser.equals(subject) && !isTokenExpired(token));
    }

    public boolean isTokenExpired (String token){
        return extractExpirationDate(token).before(new Date());
    }

    public Date extractExpirationDate (String token){
        return extractClaims(token).getExpiration();
    }

    public String extractTokenFromCookies(Cookie[] cookies){
        String cookieToken = "";
        if (cookies != null ){
            for (Cookie cookie : cookies){
                if ("Authorization".equals(cookie.getName())){
                     cookieToken = cookie.getValue();
                    break;
                }
            }
        }
        return cookieToken;
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                //Establece la firma con la que se debe hacer el Parser
                .verifyWith(key())
                //Finaliza la configuracion del parser con el setSigningKey
                .build()
                /*Analiza el token, compara la firma con la que se configuro el parser con la del token,
                extrae los claims del token*/
                .parseSignedClaims(token)
                //Obtiene los claims y devuelve un objeto Claims
                .getPayload();
    }

    public String extractClaim (String token, String claim) {
        return extractClaims(token).get(claim, String.class);
    }

    public String extractIdUser(String token) {
        return extractClaims(token).getSubject();
    }

    public String extractUsername(String token)  {
        /*return extractClaims(token).getSubject();*/
        return extractClaim(token, "username");
    }

    public String extractPosition(String token) {
        return extractClaim(token, "position");
    }

}

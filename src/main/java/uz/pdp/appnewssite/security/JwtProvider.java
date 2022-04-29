package uz.pdp.appnewssite.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.pdp.appnewssite.entity.Lavozim;

import java.util.Date;
import java.util.Set;

@Component
public class JwtProvider {
    private final String keyWord ="keyWord";
    private long expireTime = 1000*60*60*24;
    private Date expireDate=new Date(System.currentTimeMillis()+expireTime);

    public String generateToken(String username, Lavozim lavozims){
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .claim("roles", lavozims.getName())
                .signWith(SignatureAlgorithm.HS512, keyWord)
                .compact();
        return token;
    }

    public String getUsernameFromToken(String token){
        try {
            String username = Jwts
                    .parser()
                    .setSigningKey(keyWord)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return username;
        }catch (Exception e){
            return null;
        }
    }
}

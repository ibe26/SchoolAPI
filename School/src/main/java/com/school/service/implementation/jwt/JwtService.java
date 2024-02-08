package com.school.service.implementation.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY="UwwhKZ5UMrd44SvZX/a52kdkiqtbb8dcY2aq5kki2acaD/aBK9lZv1yUWTas8/1NOKPY4mOq7HjMte7KDhXX0lUc8RrH8eBPs63an1ZSE4M23fNErTFiWniPGxdnQ5SoS/VQJeLUQdJqhhAR+RkctfXPBlxyAMHpOKZ/vZwDAyEJQ+IIY6vZcuYVL6k/it9QFZaP1JoY/ns5kIHdNr2Bpajj3vZsRECT3AtQkSv6FWYmnxcUTEtJgmhTFfaiJe/eig2w24EqRBtaYPGPlZ5uD9vpjtuwYy6hiWV3isTe6Od1x/dxHVVKMklNn2Q0C5SEluM8e1iE46oofVV2+RfaKAog2JVeWg/EVJGPsR+oAeY=";
    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }
    public String generateToken(
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimResolver){
        final Claims claims=extractAllClaims(token);
        return claimResolver.apply(claims);
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }


    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

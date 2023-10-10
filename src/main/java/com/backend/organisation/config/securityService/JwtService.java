package com.backend.organisation.config.securityService;

import com.backend.organisation.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String secretKey = "YWhyb3JudXJpZGRpbm92MjYzMDExaHVzbmlkZGlubydnJ2xpZGFzdHVyY2hpdGFsYWJhdmFob3ppcnRpbXVyYmlsYW5uaXJrdmFydGlyYWRhY2hpcmNoaXFkYXlhc2hheWFwdGl5YXFpbmRhaXRhbGl5YXZpemFkYW5hdGthem9sZGlrZXlpbmdpeWlsaHVkb2hvaGxhc2FjaGV0ZWxnYWtldGFkaQ";

    public String generateToken(User user){
        return generateToken(new HashMap<>(),user);
    }

    public String generateToken(Map<String,Claims> extraClaim, User user){
        return Jwts
                .builder()
                .setClaims(extraClaim)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 10000 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

    }
    public boolean isTokenValidate(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

package com.ssc.utility;

import com.ssc.dto.CustomUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtUtility {

    private final EncryptEncoder encryptEncoder;
    private String secretKey;

    public String createToken(Authentication authentication) {
        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        ClaimsBuilder claims = Jwts.claims();
        claims.add("auth", authentication.getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .reduce("", (prev, cur) -> prev.isEmpty() ? cur : prev + "," + cur));
        claims.add("id", ((CustomUser)authentication.getPrincipal()).getId());
        Date now = new Date();

        long expirationTime = 180L;
        return Jwts.builder()
            .claims(claims.build())
            .issuedAt(now)
            .expiration(new Date(now.getTime() + expirationTime * 60 * 1000))
            .signWith(key)
            .compact();

    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        String auth = (String) claims.get("auth");
        UUID id = (UUID) claims.get("id");
        List<GrantedAuthority> authorities = Arrays.stream(auth.split(","))
            .map(s -> (GrantedAuthority)new SimpleGrantedAuthority(s))
            .toList();

        UserDetails principal = new CustomUser(id, claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public Claims getClaims(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        return Jwts.parser()
            .decryptWith(key)
            .build()
            .parseSignedClaims(token).getPayload();
    }

}

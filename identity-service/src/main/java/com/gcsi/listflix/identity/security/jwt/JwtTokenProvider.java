package com.gcsi.listflix.identity.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * @author Gary Cheng
 */
@Component
@Slf4j
public class JwtTokenProvider {
    private static final String AUTHORITIES_KEY = "roles";

    private Long expirationTime;
    private Key secretKey;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") Long expirationTime) {
        this.expirationTime = expirationTime;
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(Authentication authentication) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationTime * 1000);
        Claims claims = Jwts.claims().setSubject(authentication.getName());
        if (!authentication.getAuthorities().isEmpty()) {
            claims.put(AUTHORITIES_KEY, authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority).collect(joining(",")));
        }
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(this.secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = this.getClaims(token).getBody();
        List<GrantedAuthority> authorities = this.getAuthorities(claims);
        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public Boolean validateToken(String token) {
        try {
            Jws<Claims> claims = this.getClaims(token);
            // parseClaimsJws will check expiration date. No need do here.
            log.info("Token expiration date: {}", claims.getBody().getExpiration());
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        }
        return false;
    }

    public List<GrantedAuthority> getAuthorities(String token) {
        return this.getAuthorities(this.getClaims(token).getBody());
    }

    private Jws<Claims> getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(this.secretKey).build().parseClaimsJws(token);
    }
    private List<GrantedAuthority> getAuthorities(Claims claims) {
        Object authoritiesClaim = claims.get(AUTHORITIES_KEY);
        return authoritiesClaim == null
                ? AuthorityUtils.NO_AUTHORITIES
                : AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesClaim.toString());
    }
}

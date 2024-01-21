package com.ecommerce.controllers.security;


import com.ecommerce.adapters.security.AuthenticationAdapter;
import com.ecommerce.adapters.security.UserDetailsImpl;
import com.ecommerce.domain.util.message.ErrorMessages;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${jwtSecret}")
    private String jwtSecret;

    @Value("${jwtExpirationMs}")
    private int jwtExpirationMs;


    @Autowired
    AuthenticationAdapter userPrincipalService;

    public JwtUtils(AuthenticationAdapter userPrincipalService) {
        this.userPrincipalService = userPrincipalService;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }


    public String generateToken(String userEmail, String role){

        Claims claims = Jwts.claims().setSubject(userEmail);
        claims.put("role", role);
        Date DateCreated = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(DateCreated)
                .setExpiration(new Date(DateCreated.getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (MalformedJwtException e) {
            logger.error(ErrorMessages.INVALID_JWT_TOKEN + " {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error(ErrorMessages.EXPIRED_JWT_TOKEN + " {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error(ErrorMessages.UNSUPPORTED_JWT_TOKEN + " {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error(ErrorMessages.EMPTY_JWT_CLAIMS + " {}", e.getMessage());
        }
        return false;
    }

    public String resolveToken(HttpServletRequest request){
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public Authentication getAuthentication(String token){

        UserDetailsImpl userDetailsimpl = userPrincipalService.loadUserByUsername(getUserNameFromJwtToken(token));
        return new UsernamePasswordAuthenticationToken(userDetailsimpl, null, userDetailsimpl.getAuthorities());
    }
}

package com.rcr.ecommerce.Configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtTokenProvider {
    public static final String SECRET_KEY = "ieueiuwyjsjdcbjhgddkhfgvnbvncbvdjhfeieurtgilurehddjvbd";
    public static final String JWT_HEADER = "Authorization";

    private SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(Authentication auth){
        Collection<?extends GrantedAuthority> authorities = auth.getAuthorities();

        String roles = populateAuthorities(authorities);

        String jwt = Jwts.builder().setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime()+86400000))
                .claim("email",auth.getName()).claim("authorities",roles).signWith(key).compact();

        return jwt;
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auths = new HashSet<>();
        for(GrantedAuthority authority :authorities){
            auths.add(authority.getAuthority());
        }
        return String.join(",",auths);
    }

    public String findEmailByJwtToken(String jwt){
        String jwt1 = jwt.substring(7);
        Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt1).getBody();
        String email = String.valueOf(claims.get("email"));

        return email;
    }
}

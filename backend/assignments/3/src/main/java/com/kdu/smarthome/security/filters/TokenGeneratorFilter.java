package com.kdu.smarthome.security.filters;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * A custom filter to generate and attach JWT (JSON Web Token) to the response header.
 */
@Component
public class TokenGeneratorFilter extends OncePerRequestFilter {

    /**
     * Constant representing the JWT header key.
     */
    public static final String JWT_HEADER = "Authorization";

    /**
     * Constant representing the secret key for JWT generation.
     */
    public static final String JWT_KEY = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";

    /**
     * Performs the actual filtering for every incoming request.
     *
     * @param request     The incoming HTTP request.
     * @param response    The HTTP response.
     * @param filterChain The filter chain for additional filters.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs during the filtering process.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {
            String jwt = generateJWT(authentication);
            response.setHeader(JWT_HEADER, "Bearer ".concat(jwt));
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Generates a JWT using the provided authentication information.
     *
     * @param authentication The authentication object containing user details.
     * @return The generated JWT as a string.
     */
    public String generateJWT(Authentication authentication) {
        SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder().issuer("kdu").subject("JWT Token")
                .claim("username", authentication.getName())
                .claim("roles", populateAuthorities(authentication.getAuthorities()))
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + 30000000))
                .signWith(key).compact();
    }

    /**
     * Converts a collection of granted authorities to a comma-separated string.
     *
     * @param collection The collection of granted authorities.
     * @return A comma-separated string representing the authorities.
     */
    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }
}

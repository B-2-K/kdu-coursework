package com.kdu.smarthome.security.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * A custom filter to validate and process JWT tokens for incoming requests.
 */
@Component
public class TokenValidatorFilter extends OncePerRequestFilter {

    /**
     * Constant representing the JWT header key.
     */
    public static final String JWT_HEADER = "Authorization";

    /**
     * Constant representing the secret key for JWT validation.
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
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        if (path.equals("/api/v1/auth/register")) {
            filterChain.doFilter(request, response);
            return;
        }
        String jwtWithBearer = request.getHeader(JWT_HEADER);
        if (jwtWithBearer != null) {
            try {
                String jwt = jwtWithBearer.substring(7);
                SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));

                Claims claims = Jwts.parser()
                        .verifyWith(key)
                        .build()
                        .parseSignedClaims(jwt)
                        .getPayload();
                String username = String.valueOf(claims.get("username"));
                String authorities = (String) claims.get("roles");
                Authentication auth = new UsernamePasswordAuthenticationToken(username, null,
                        AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                throw new BadCredentialsException("Invalid Token received!");
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid/Empty token");
            return;
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Determines if the filter should not be applied based on the request path.
     *
     * @param request The incoming HTTP request.
     * @return True if the filter should not be applied, false otherwise.
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().equals("/api/v1/auth/register");
    }
}

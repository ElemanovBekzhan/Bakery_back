package org.example.subd.jwt;


import jakarta.servlet.ServletException;
import org.example.subd.service.EmployeeDetailsService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{
    private final EmployeeDetailsService userDetailsService;
    private final JwtUtils jwtUtil;

    public JwtAuthFilter(EmployeeDetailsService uds, JwtUtils ju) {
        this.userDetailsService = uds;
        this.jwtUtil = ju;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws java.io.IOException, jakarta.servlet.ServletException {
        String header = req.getHeader("Authorization");
        String token = null, username = null;
        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
            try { username = jwtUtil.extractUsername(token); }
            catch (ExpiredJwtException ignored) {}
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails user = userDetailsService.loadUserByUsername(username);
            if (jwtUtil.validateToken(token, (EmployeeDetails) user)) {
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(req, res);
    }
}

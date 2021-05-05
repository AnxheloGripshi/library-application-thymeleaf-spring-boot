package com.library.config.security;

import com.library.services.impl.CustomUserDetailsService;
import com.library.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //first we need to extract the authorization header from the request
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String token = null;
        String username = null;
        //Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYXZhdGVjaGllIiwiZXhwIjoxNjE5NTQ5MzEwLCJpYXQiOjE2MTk1MTMzMTB9.znSCfDdYvB7JMdjvBaVZvRkGnCs6YIZc_MowDO0fuLE
        //we only need to extract the token, not the "Bearer " part.
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            token = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(token);
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){ //??
           UserDetails userDetails =  userDetailsService.loadUserByUsername(username);

           if(jwtUtil.validateToken(token, userDetails)) { //if the token is valid, we validate user details,
               //if the user data is valid, we set it to the context holder
               UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                       userDetails, null, userDetails.getAuthorities()
               );
               usernamePasswordAuthenticationToken
                       .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
               SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
           }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

package com.api.login.auth.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.api.login.auth.service.TokenService;
import com.api.login.usuario.repository.UsuarioRepository;
import com.auth0.jwt.exceptions.JWTVerificationException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService service;

    @Autowired
    UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                var token  = this.recoverToken(request);
                 if(token != null){
                    var login = service.validateToken(token);                  
                   UserDetails user = repository.findByUsuario(login);                                          
                 var authentication = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());   
                SecurityContextHolder.getContext().setAuthentication(authentication);
                 }
                filterChain.doFilter(request, response);
    
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");       
        if(authHeader == null)throw new JWTVerificationException("Token não encontrado!");       
        return authHeader.replace("Bearer ", "");
    }
    
}

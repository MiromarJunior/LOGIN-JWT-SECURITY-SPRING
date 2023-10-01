package com.api.login.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.login.auth.model.LoginResponseDto;
import com.api.login.auth.model.dto.AuthDto;
import com.api.login.auth.service.TokenService;
import com.api.login.usuario.model.UsuarioWeb;
import com.api.login.usuario.repository.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("login")
public class AuthController {

  @Autowired
  private AuthenticationManager manager;

  @Autowired
  private TokenService tokenService;

  @Autowired
  private UsuarioRepository repository;

  @PostMapping
  ResponseEntity<?> loginUsuario(@RequestBody @Valid AuthDto dto) {
 
      var userNamePassword = new UsernamePasswordAuthenticationToken(dto.usuario(), dto.senhaUsuario());
      var auth = manager.authenticate(userNamePassword);
      var token = tokenService.generateToken((UsuarioWeb) auth.getPrincipal());
      return ResponseEntity.ok(new LoginResponseDto(token));   

  }

  @GetMapping("")
  public ResponseEntity<List<UsuarioWeb>> getAll() {
    List<UsuarioWeb> usuarios = repository.findAll();
    return ResponseEntity.ok().body(usuarios);
  }

}

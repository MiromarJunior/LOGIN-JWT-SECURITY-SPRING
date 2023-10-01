package com.api.login.usuario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.api.login.usuario.model.UsuarioWeb;
import com.api.login.usuario.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;



    @PostMapping
    public ResponseEntity<UsuarioWeb> saveUsu(@RequestBody UsuarioWeb usuarioWeb){    
      
      String encryptString = new BCryptPasswordEncoder().encode(usuarioWeb.getSenhaUsuario());
      usuarioWeb.setSenhaUsuario(encryptString);
      UsuarioWeb usu = service.saveUser(usuarioWeb);
      return ResponseEntity.ok().body(usu)  ;

    }

    @GetMapping("/get")
    public String getAll(){
     
      return "Login efetudado";
    }


    
}

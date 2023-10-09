package com.api.login.usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.api.login.usuario.model.UsuarioWeb;
import com.api.login.usuario.model.dto.UsuarioCreateDTO;
import com.api.login.usuario.model.dto.UsuarioGetDTO;
import com.api.login.usuario.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;



    @PostMapping
    public ResponseEntity<UsuarioWeb> saveUsu(@RequestBody UsuarioCreateDTO dto){    
      

      UsuarioWeb usu = service.createUser(dto);
      return ResponseEntity.ok().body(usu)  ;

    }

    @GetMapping("")
    public ResponseEntity<List<UsuarioGetDTO>>  getAll(){
      List<UsuarioGetDTO> usuarios = service.getAll();

      return ResponseEntity.ok().body(usuarios);
     
    
    }


    
}

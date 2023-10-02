package com.api.login.usuario.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.api.login.usuario.model.UsuarioWeb;
import com.api.login.usuario.model.dto.UsuarioGetDTO;
import com.api.login.usuario.repository.UsuarioRepository;



@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioWeb saveUser(UsuarioWeb usuarioWeb){ 
      if(repository.findByUsuario(usuarioWeb.getUsuario())!= null){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Não é possivel Cadastrar esse usuário");
      }
      return  repository.save(usuarioWeb);
    }

    // public List<UsuarioGetDTO> getAll(){
    //   List<UsuarioWeb> usuarios = repository.findAll();
    //   return usuarios.stream().map(user-> new UsuarioGetDTO(user.getNomeUsuario(),user.getUsuario(),user.getUsuarioAdm())).collect(Collectors.toList());
    // }
    
        public List<UsuarioGetDTO> getAll(){
      List<UsuarioWeb> usuarios = repository.findAll();
      return usuarios.stream().map(UsuarioGetDTO::new).collect(Collectors.toList());
    }
    
}

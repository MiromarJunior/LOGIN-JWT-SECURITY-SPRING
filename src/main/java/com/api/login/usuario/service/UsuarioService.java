package com.api.login.usuario.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.api.login.usuario.model.UsuarioWeb;
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

    public List<UsuarioWeb> getAll(){
      return repository.findAll();
    }
    
    
}

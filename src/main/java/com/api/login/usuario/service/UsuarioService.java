package com.api.login.usuario.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.api.login.usuario.model.UsuarioWeb;
import com.api.login.usuario.model.dto.UsuarioCreateDTO;
import com.api.login.usuario.model.dto.UsuarioGetDTO;
import com.api.login.usuario.repository.UsuarioRepository;



@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioWeb createUser(UsuarioCreateDTO dto){ 
      if(repository.findByUsuario(dto.usuario())!= null){
        throw new  ResponseStatusException(HttpStatus.CONFLICT,"Não é possivel Cadastrar esse usuário\nUsuário já existe na base!");
      }
   
      UsuarioWeb usuario = new UsuarioWeb();
      BeanUtils.copyProperties(dto, usuario,"dtInclusao","usuarioAdm","cpfUsuario","fone","senhaUsuario");
      usuario.setDtInclusao(LocalDateTime.now());
      usuario.setUsuarioAdm("Nao");
      usuario.setCpfUsuario(apenasNumeros(dto.cpfUsuario()));
      usuario.setFone(apenasNumeros(dto.fone()));
      usuario.setSenhaUsuario(cryptoSenha(dto.senhaUsuario()));
      return  repository.save(usuario);
    }

    // public List<UsuarioGetDTO> getAll(){
    //   List<UsuarioWeb> usuarios = repository.findAll();
    //   return usuarios.stream().map(user-> new UsuarioGetDTO(user.getNomeUsuario(),user.getUsuario(),user.getUsuarioAdm())).collect(Collectors.toList());
    // }
    
        public List<UsuarioGetDTO> getAll(){
      List<UsuarioWeb> usuarios = repository.findAll();
      return usuarios.stream().map(UsuarioGetDTO::new).collect(Collectors.toList());
    }

    public String apenasNumeros(String value) {
      if (value != null) {
          return value.replaceAll("\\D+", "");
      } else {
          return value;
      }

  }

  private String cryptoSenha(String senha){
    String encryptString = new BCryptPasswordEncoder().encode(senha);
    return encryptString;
  }
    
}

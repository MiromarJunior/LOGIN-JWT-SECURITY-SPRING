package com.api.login.usuario.model.dto;

import com.api.login.usuario.model.UsuarioWeb;

public record UsuarioGetDTO(String nomeUsuario, String usuario, String usuarioAdm) {


    public UsuarioGetDTO(UsuarioWeb usu){
        this(usu.getNomeUsuario(), usu.getUsuario(), usu.getUsuarioAdm()); 
    }
    
}

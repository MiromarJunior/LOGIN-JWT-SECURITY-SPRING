package com.api.login.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.login.usuario.model.UsuarioWeb;

public interface UsuarioRepository extends JpaRepository<UsuarioWeb,Long>{

 UserDetails findByUsuario(String userName);
    
}
 
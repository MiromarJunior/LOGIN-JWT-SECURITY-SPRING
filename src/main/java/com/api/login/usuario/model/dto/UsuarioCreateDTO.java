package com.api.login.usuario.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



public record UsuarioCreateDTO(
     String nomeUsuario,
     String usuario,
    //  String usuarioAdm,
     String senhaUsuario,
    //  LocalDateTime dtInclusao,
     String cpfUsuario,
     LocalDate dtNascimento,
     String fone,
     String email,
     String cep,
     String logradouro,
     String nrLogradouro,
     String complemento,
     String cidade,
    //  String acessoWeb,
    //  String termoAceito,
    //  String statusUsuario,
     String ibgeCidade,
     String usuarioInsert,
     String idUsuarioInclusao,
     String uf,
     String bairro


){
   
 }

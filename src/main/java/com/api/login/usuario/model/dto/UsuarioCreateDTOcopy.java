package com.api.login.usuario.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCreateDTOcopy{
    private String nomeUsuario;
    private String usuario;
    // private String usuarioAdm;
    private String senhaUsuario;
    // private LocalDateTime dtInclusao;
    private String cpfUsuario;
    private LocalDate dtNascimento;
    private String fone;
    private String email;
    private String cep;
    private String logradouro;
    private String nrLogradouro;
    private String complemento;
    private String cidade;
    // private String acessoWeb;
    // private String termoAceito;
    // private String statusUsuario;
    private String ibgeCidade;
    private String usuarioInsert;
    private String idUsuarioInclusao;
    private String uf;
    private String bairro;
 }

package com.api.login.usuario.model.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record UsuarioCreateDTO(
          @NotBlank(message = "Campo nomeUsuário Obrigatorio") 
          String nomeUsuario,

          @NotBlank(message = "Campo usuario Obrigatorio") 
          String usuario,
          // String usuarioAdm,
          @NotBlank(message = "Campo senha Obrigatorio") 
          String senhaUsuario,
          // LocalDateTime dtInclusao,
          @CPF(message = "CPF inválido")
          String cpfUsuario,

          @NotNull(message = "A data de nascimento é obrigatória.")
          @DateTimeFormat(pattern = "yyyy-MM-dd")
          @Past(message = "Data tem que ser menor que a atual")
          LocalDate dtNascimento,

          String fone,

          @Email(message = "Email inválido")
          String email,
          String cep,
          String logradouro,
          String nrLogradouro,
          String complemento,
          String cidade,
          // String acessoWeb,
          // String termoAceito,
          // String statusUsuario,
          String ibgeCidade,
          String usuarioInsert,
          String idUsuarioInclusao,
          String uf,
          String bairro

) {

}

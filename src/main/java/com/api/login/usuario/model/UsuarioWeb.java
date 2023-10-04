package com.api.login.usuario.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioWeb implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioWebId;

    @Column(name = "USWE_NOME")
    private String nomeUsuario;

    @Column(name = "USWE_USUARIO")
    private String usuario;

    @JsonIgnore
    @Column(name = "USWE_SENHA")
    private String senhaUsuario;

    @Column(name = "USWE_ADM")
    private String usuarioAdm;

    @Column(name = "USWE_DT_ALTERACAO")
    private LocalDateTime dtAlteracao;

    @Column(name = "USWE_DT_RECUPERACAO")
    private LocalDateTime dtRecuperacao;

    @Column(name = "USWE_DT_EXCLUSAO")
    private LocalDateTime dtExclusao;

    @Column(name = "USWE_DT_INCLUSAO")
    private LocalDateTime dtInclusao;

    @Column(name = "USWE_CPF")
    private String cpfUsuario;

    @Column(name = "USWE_DT_NASCIMENTO")
    private LocalDate dtNascimento;

    @Column(name = "USWE_FONE")
    private String fone;

    @Column(name = "USWE_EMAIL")
    private String email;

    @Column(name = "USWE_USUARIO_RECUPERACAO_ID")
    private Long usuarioRecuperacao;

    @Column(name = "USWE_CEP")
    private String cep;

    @Column(name = "USWE_LOGRADOURO")
    private String logradouro;

    @Column(name = "USWE_NR_LOGRADOURO")
    private String nrLogradouro;

    @Column(name = "USWE_COMPLEMENTO")
    private String complemento;

    @Column(name = "USWE_CIDADE")
    private String cidade;

    @Column(name = "USWE_ACESSO_WEB")
    private String acessoWeb;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (getUsuarioAdm().equals("Sim"))
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return getSenhaUsuario();
    }

    @Override
    public String getUsername() {
        return getUsuario();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

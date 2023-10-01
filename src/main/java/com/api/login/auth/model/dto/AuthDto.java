package com.api.login.auth.model.dto;

import jakarta.validation.constraints.NotNull;

public record AuthDto( @NotNull(message = "Campo Obrigatorio") String usuario, @NotNull(message = "Campo Obrigatorio") String senhaUsuario) {



}

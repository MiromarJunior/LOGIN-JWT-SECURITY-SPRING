package com.api.login.tratamentoexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

@RestControllerAdvice
public class ErrorHandling {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratarErroValidacao(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();
        var response = errors.stream().map(DadosErros::new).toList();
        return ResponseEntity.badRequest().body(response);

    }

    
    @ExceptionHandler({InternalAuthenticationServiceException.class,BadCredentialsException.class})
    public ResponseEntity<?> tratarErroLogin() {
        return new ResponseEntity<>(new LoginInvalidoDTO("Usuário e/ou Senha inválido(s)!"), HttpStatus.UNAUTHORIZED);
    }


    private record DadosErros(String message, String field) {
        public DadosErros(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

        @ExceptionHandler(JWTVerificationException.class)
        public ResponseEntity<?> tratarErroTokenExpire() {
        return new ResponseEntity<>(new TokenExpiredDTO("Token Expirado Favor efetuar um novo login!"),HttpStatus.UNAUTHORIZED);

    }


    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<String> handleTokenExpiredException(TokenExpiredException ex) {
        String errorMessage = "O Token expirou em " + ex.getExpiredOn() + ".";
        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

}

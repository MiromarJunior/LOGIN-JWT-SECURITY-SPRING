package com.api.login.tratamentoexception;

import java.io.IOException;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ErrorHandling  implements AuthenticationEntryPoint {

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


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleTokenExpiredException(TokenExpiredException ex) {
        String errorMessage = "O Token expirou em " + ex.getExpiredOn() + ".";
        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
  
        response.setContentType("application/json;charset=UTF-8");
     response.setStatus(403);

     ObjectMapper mapper = new ObjectMapper();
     response.getWriter().write(mapper.writeValueAsString(new LoginInvalidoDTO("Acesso Não Autorizado"))
     );
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<MensagemErroDTO> handleResponseStatusException(ResponseStatusException ex) {
        // Aqui você pode personalizar a mensagem de erro conforme necessário
        String mensagemDeErro = ex.getMessage();
        
        // Você pode também obter o código de status HTTP da exceção
         int codigoDeStatus = ex.getStatusCode().value();
        
        // Crie uma resposta ResponseEntity com a mensagem de erro e o código de status HTTP
        return ResponseEntity.status(codigoDeStatus).body(new MensagemErroDTO(mensagemDeErro));
    }

    

}

package br.edu.ifsc.chamados.configs.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends DefaultException {

    public UnauthorizedException() {
    }

    @Override
    public String getMessage() {
        return String.format("Credenciais invalidas, tente novamente.");
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }

}

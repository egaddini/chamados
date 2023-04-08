package br.edu.ifsc.chamados.configs.exceptions;

import org.springframework.http.HttpStatus;

public class RegisterUser2Exception extends DefaultException {

    private String campo;

    public RegisterUser2Exception(String campo) {
        this.campo = campo;
    }

    @Override
    public String getMessage() {
        return String.format("Não é possível continuar, %s já utilizado", campo);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }
}

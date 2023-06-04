package br.edu.ifsc.chamados.configs.exceptions;

import org.springframework.http.HttpStatus;

public class RegisterUser2Exception extends DefaultException {

    private String campo;

    private String valor;

    public RegisterUser2Exception(String campo, String valor) {
        this.campo = campo;
        this.valor = valor;
    }

    @Override
    public String getMessage() {
        return String.format("Não é possível Registrar, %s: %s já registrado.", campo, valor);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }
}

package br.edu.ifsc.chamados.configs.exceptions;

import org.springframework.http.HttpStatus;

public class RecordNotFound2Exception extends DefaultException {

    private String id;
    public RecordNotFound2Exception(String id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Registro não encontrado id: " + id;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}

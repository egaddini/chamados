package br.edu.ifsc.chamados.configs.exceptions;

import org.springframework.http.HttpStatus;

public class InactiveUser2Exception extends DefaultException {

    private String user;

    public InactiveUser2Exception(String user) {
        this.user = user;
    }

    @Override
    public String getMessage() {
        return String.format("Conta: %s inativa.\nCaso seja uma nova conta aguarde até Xhoras ou entre em contato com o suporte.", user);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }

}

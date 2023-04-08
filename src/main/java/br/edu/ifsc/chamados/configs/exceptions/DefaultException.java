package br.edu.ifsc.chamados.configs.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public abstract class DefaultException extends Exception {

    private String message;
    private HttpStatus status;

}

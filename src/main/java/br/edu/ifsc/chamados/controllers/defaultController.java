package br.edu.ifsc.chamados.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class defaultController {

    @GetMapping("/")
    public ResponseEntity<String> index() {
        String corpoResposta = "Olá, mundo!";
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(corpoResposta, status);
    }

    @GetMapping("/only-authenticated")
    public String depois() {
        return "hello world";
    }
}

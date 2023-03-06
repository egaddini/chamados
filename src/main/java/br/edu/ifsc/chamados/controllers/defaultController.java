package br.edu.ifsc.chamados.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class defaultController {

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String index() {
        return "hello world";
    }
    //@PreAuthorize("hasHole('ROLE_ADMIN')")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/only-authenticated")
    public String depois() {
        return "hello world";
    }
}

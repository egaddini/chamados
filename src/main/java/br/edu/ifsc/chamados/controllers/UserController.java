package br.edu.ifsc.chamados.controllers;

import br.edu.ifsc.chamados.models.user.User;
import br.edu.ifsc.chamados.models.user.UserDTOImpl;
import br.edu.ifsc.chamados.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.RequestPath;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl usvc;

    @PostMapping("/user")
    public User saveUser(@RequestBody UserDTOImpl user) {
        return usvc.saveUser(user);
    }

    @GetMapping("user/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        return usvc.getUser(id);
    }
}

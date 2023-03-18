package br.edu.ifsc.chamados.controllers;

import br.edu.ifsc.chamados.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService usvc;

    /*@PostMapping("/user")
    public User saveUser(@RequestBody UserDTOImpl user) {
        return null; //usvc.saveUser(user);
    }

    @GetMapping("user/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        return null; //usvc.getUser(id);
    }*/
}

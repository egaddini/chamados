package br.edu.ifsc.chamados.controllers;

import br.edu.ifsc.chamados.api.controllers.user.UserControllerV1;
import br.edu.ifsc.chamados.models.user.User;
import br.edu.ifsc.chamados.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(UserControllerV1.BASE_PATH)
public class UserControllerV1Impl implements UserControllerV1 {
    @Autowired
    private UserRepository userRepo;

    @GetMapping
    private ResponseEntity<Stream<User>> getUsers(Pageable pageable) {
        return new ResponseEntity<Stream<User>>(userRepo.findAll(pageable).stream(), HttpStatus.OK);
    }

//    @GetMapping("user/{id}")
//    public User getUser(@PathVariable("id") Integer id) {
//        return null; //usvc.getUser(id);
//    }
}

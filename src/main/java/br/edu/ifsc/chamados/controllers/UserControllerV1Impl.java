package br.edu.ifsc.chamados.controllers;

import br.edu.ifsc.chamados.api.controllers.user.UserControllerV1;
import br.edu.ifsc.chamados.api.services.user.IUserService;
import br.edu.ifsc.chamados.configs.exceptions.DefaultException;
import br.edu.ifsc.chamados.configs.exceptions.RecordNotFound2Exception;
import br.edu.ifsc.chamados.models.user.User;
import br.edu.ifsc.chamados.requests.RegisterRequest;
import br.edu.ifsc.chamados.response.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(UserControllerV1.BASE_PATH)
public class UserControllerV1Impl implements UserControllerV1 {

    private final IUserService userSvc;

//    @GetMapping
//    private ResponseEntity<Page<User>> getUsers(Pageable pageable) {
//        return new ResponseEntity<Page<User>>(userSvc.findUsers(pageable), HttpStatus.OK);
//    }
    @GetMapping()
    private List<UserResponse> getUsers() {
        return userSvc.findUsers();
    }

    @DeleteMapping(ID_PATH)
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id) {
        userSvc.deleteUser(id);
        return new ResponseEntity<String>("Usuário removido com sucesso", HttpStatus.OK);
    }

    @GetMapping(ID_PATH)
    public User getUser(@PathVariable("id") Integer id) throws RecordNotFound2Exception {
        return userSvc.findUserById(id);
    }

    @PutMapping(ID_PATH)
    public ResponseEntity<UserResponse> updateUser(@PathVariable("id") Integer id, @RequestBody RegisterRequest request) throws DefaultException {
        return new ResponseEntity<UserResponse>(userSvc.updateUser(request, id), HttpStatus.OK);
    }

    @GetMapping(EMAIL_PATH)
    public User getUserByEmail(@PathVariable("email") String email) throws RecordNotFound2Exception {
        return userSvc.findUserByEmail(email);
    }

}

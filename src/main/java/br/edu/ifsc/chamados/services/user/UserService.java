package br.edu.ifsc.chamados.services.user;

import br.edu.ifsc.chamados.api.models.user.IUser;
import br.edu.ifsc.chamados.configs.BeanScope;
import br.edu.ifsc.chamados.enums.Role;
import br.edu.ifsc.chamados.models.user.User;
import br.edu.ifsc.chamados.repositories.UserRepository;
import br.edu.ifsc.chamados.requests.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;
import java.util.List;

@Service
//@Scope(BeanScope.PROTOTYPE)
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(RegisterRequest request) {

        User existUser = repository.findByEmail(request.getEmail()).orElseThrow(() -> new FindException("Email já registrado"));

        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        return repository.save(user);
    }

    public User findUsers() {
        String email = "edder@gmail.com";
        return repository.findByEmail(email).get();
    }

}

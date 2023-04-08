package br.edu.ifsc.chamados.services.user;

import br.edu.ifsc.chamados.api.services.user.IUserService;
import br.edu.ifsc.chamados.configs.BeanScope;
import br.edu.ifsc.chamados.configs.exceptions.DefaultException;
import br.edu.ifsc.chamados.configs.exceptions.RecordNotFound2Exception;
import br.edu.ifsc.chamados.configs.exceptions.RegisterUser2Exception;
import br.edu.ifsc.chamados.enums.Role;
import br.edu.ifsc.chamados.models.user.User;
import br.edu.ifsc.chamados.repositories.UserRepository;
import br.edu.ifsc.chamados.requests.RegisterRequest;
import br.edu.ifsc.chamados.response.user.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Scope(BeanScope.PROTOTYPE)
public class UserService implements IUserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponse> findUsers() {
        List<User> users = repository.findAll();
        return users.stream().map(i -> new UserResponse(i.getId(), i.getFirstname(), i.getLastname(), i.getEmail(), i.getRole())).collect(Collectors.toList());
    }
    @Override
    public User saveUser(RegisterRequest request) throws Exception {

        validEmail(request.getEmail());

        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        return repository.save(user);
    }
    @Override
    public UserResponse updateUser(RegisterRequest userUpdt, Integer id) throws DefaultException {

        User user = findUserById(id);
        boolean isChanged = false;

        if (!user.getEmail().equals(userUpdt.getEmail())) {
            user.setEmail(userUpdt.getEmail());
            validEmail(userUpdt.getEmail());
            isChanged = true;
        }
        if (!user.getFirstname().equals(userUpdt.getFirstname())) {
            user.setFirstname(userUpdt.getFirstname());
            isChanged = true;
        }
        if (!user.getLastname().equals(userUpdt.getLastname())) {
            user.setLastname(userUpdt.getLastname());
            isChanged = true;
        }
        if (!user.getPassword().equals(passwordEncoder.encode(userUpdt.getPassword()))) {
            user.setPassword(passwordEncoder.encode(userUpdt.getPassword()));
            isChanged = true;
        }

        if (isChanged) repository.save(user);

        return new UserResponse(user);
    }
    @Override
    public void deleteUser(Integer id) {
        repository.deleteById(id);
    }
    @Override
    public User findUserById(Integer id) throws RecordNotFound2Exception {
        return repository.findById(id).orElseThrow(() -> new RecordNotFound2Exception(id.toString()));
    }
    @Override
    public User findUserByEmail(String email) throws RecordNotFound2Exception {
        return repository.findByEmail(email).orElseThrow(() -> new RecordNotFound2Exception(email));
    }
    private void validEmail(String email) throws RegisterUser2Exception {
        Optional<User> existUser = repository.findByEmail(email);
        if (!existUser.isEmpty()) throw new RegisterUser2Exception("Email");
    }

}

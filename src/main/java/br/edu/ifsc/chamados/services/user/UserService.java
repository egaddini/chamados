package br.edu.ifsc.chamados.services.user;

import br.edu.ifsc.chamados.api.services.user.IUserService;
import br.edu.ifsc.chamados.configs.BeanScope;
import br.edu.ifsc.chamados.configs.exceptions.DefaultException;
import br.edu.ifsc.chamados.configs.exceptions.RecordNotFound2Exception;
import br.edu.ifsc.chamados.configs.exceptions.RegisterUser2Exception;
import br.edu.ifsc.chamados.dto.SucessDTO;
import br.edu.ifsc.chamados.enums.Role;
import br.edu.ifsc.chamados.models.call.Call;
import br.edu.ifsc.chamados.models.call.CallSector;
import br.edu.ifsc.chamados.models.user.User;
import br.edu.ifsc.chamados.models.user.UserSector;
import br.edu.ifsc.chamados.repositories.UserRepository;
import br.edu.ifsc.chamados.requests.RegisterRequest;
import br.edu.ifsc.chamados.response.user.UserResponse;
import br.edu.ifsc.chamados.response.user.UserTinyResponse;
import br.edu.ifsc.chamados.services.call.SectorService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Scope(BeanScope.PROTOTYPE)
public class UserService implements IUserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private SectorService sectorService;
    @Autowired
    private UserSectorService userSectorService;

    @Override
    public List<UserResponse> findUsers() {
        List<User> users = repository.findAll();
        List<UserResponse> response = new ArrayList<>();
        for (User user: users) {
            response.add(new UserResponse(user.getId(), user.getEmail(), user.getFirstname(), user.getLastname(), user.getPhone(),
                    user.getRole(), null, user.getActive(), user.getCreationDT(), user.getUserSector()));
        }
        return response;
    }

    @Override
    public List<UserTinyResponse> findUsersTiny() {
        List<User> users = repository.findAll();
        List<UserTinyResponse> response = new ArrayList<>();
        for (User user: users) {
            response.add(new UserTinyResponse(user.getId(), user.getEmail()));
        }
        return response;
    }
    @Override
    public User saveUser(RegisterRequest request) throws Exception {

        validEmail(request.getEmail());

        validPhone(request.getPhone());

        return repository.save(User.builder()
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .phone(request.getPhone())
            .active(false)
            .creationDT(LocalDateTime.now())
            .build());
    }
    @Transactional()
    @Override
    public UserResponse updateUser(RegisterRequest userUpdt, Integer id) throws DefaultException {

        User user = findUserById(id);
        boolean isChanged = false;

        if (userUpdt.getEmail().isBlank() && !user.getEmail().equals(userUpdt.getEmail())) {
            user.setEmail(userUpdt.getEmail());
            validEmail(userUpdt.getEmail());
            isChanged = true;
        }
        if (!userUpdt.getFirstname().isBlank() && !user.getFirstname().equals(userUpdt.getFirstname())) {
            user.setFirstname(userUpdt.getFirstname());
            isChanged = true;
        }
        if (!userUpdt.getLastname().isBlank() && !user.getLastname().equals(userUpdt.getLastname())) {
            user.setLastname(userUpdt.getLastname());
            isChanged = true;
        }
        if (!userUpdt.getPassword().isBlank() && !user.getPassword().equals(passwordEncoder.encode(userUpdt.getPassword()))) {
            user.setPassword(passwordEncoder.encode(userUpdt.getPassword()));
            isChanged = true;
        }
        if (!user.getUserSector().equals(userUpdt.getSectors())) {
            user.setUserSector(null);
            repository.saveAndFlush(user);
            userSectorService.removeUserSector(user.getId());
            Set<CallSector> sectors = sectorService.getByIds(userUpdt.getSectors().stream().map(e -> e.getId()).collect(Collectors.toList()));
            Set<UserSector> userSectors = sectors.stream().map(e -> new UserSector(null, user, e)).collect(Collectors.toSet());
            user.setUserSector(userSectors);
            isChanged = true;
        }
        if (isChanged) repository.save(user);

        return new UserResponse(user.getId(), user.getEmail(), user.getFirstname(), user.getLastname(), user.getPhone(), user.getRole(), null, user.getActive(), user.getCreationDT(), user.getUserSector());
    }
    @Override
    public void deleteUser(Integer id) throws RecordNotFound2Exception {
        repository.delete(findUserById(id));
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
        if (!existUser.isEmpty()) throw new RegisterUser2Exception("Email", email);
    }
    private void validPhone(Long phone) throws RegisterUser2Exception {
        Optional<User> existUser = repository.findByPhone(phone);
        if (!existUser.isEmpty()) throw new RegisterUser2Exception("Phone", Long.toString(phone));
    }

    @Override
    public SucessDTO ativaUsuario(String email) throws RecordNotFound2Exception {
        User user = findUserByEmail(email);
        user.setActive(user.getActive() ? false: true);
        repository.save(user);
        return new SucessDTO("Solicitação realizada com sucesso.");
    }

}

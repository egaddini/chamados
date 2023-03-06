package br.edu.ifsc.chamados.services.user;

import br.edu.ifsc.chamados.configs.security.BeanScope;
import br.edu.ifsc.chamados.enums.ERoleName;
import br.edu.ifsc.chamados.models.user.User;
import br.edu.ifsc.chamados.models.user.UserDTOImpl;
import br.edu.ifsc.chamados.repositories.RoleRepository;
import br.edu.ifsc.chamados.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Scope(BeanScope.PROTOTYPE)
public class UserServiceImpl {

    @Autowired
    UserRepository ur;

    @Autowired
    RoleRepository rr;

    public User saveUser(UserDTOImpl user) {

        return ur.save(new User().builder()
                .name(user.getName())
                .surname(user.getSurname())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                .roles(rr.findByRoleName(ERoleName.ROLE_USER))
                .build());
    }

    public User getUser(Integer id) {
        return ur.findById(id).get();
    }
}

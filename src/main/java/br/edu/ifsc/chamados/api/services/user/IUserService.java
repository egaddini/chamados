package br.edu.ifsc.chamados.api.services.user;

import br.edu.ifsc.chamados.configs.exceptions.DefaultException;
import br.edu.ifsc.chamados.configs.exceptions.RecordNotFound2Exception;
import br.edu.ifsc.chamados.dto.SucessDTO;
import br.edu.ifsc.chamados.models.user.User;
import br.edu.ifsc.chamados.requests.RegisterRequest;
import br.edu.ifsc.chamados.response.user.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    User saveUser(RegisterRequest request) throws Exception;

    UserResponse updateUser(RegisterRequest userUpdt, Integer id) throws DefaultException;

    void deleteUser(Integer id) throws RecordNotFound2Exception;

    User findUserById(Integer id) throws RecordNotFound2Exception;

    User findUserByEmail(String email) throws RecordNotFound2Exception;

    List<UserResponse> findUsers();

    SucessDTO ativaUsuario(String email) throws RecordNotFound2Exception;

}

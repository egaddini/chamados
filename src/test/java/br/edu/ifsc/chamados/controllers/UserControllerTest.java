package br.edu.ifsc.chamados.controllers;

import br.edu.ifsc.chamados.dto.SucessDTO;
import br.edu.ifsc.chamados.services.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.CREATED;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserControllerV1Impl userController;

    @InjectMocks
    private UserService userService;

    private ResponseEntity<SucessDTO> statusCriadoComSucesso;
    @BeforeEach
    void setup() {
        statusCriadoComSucesso = ResponseEntity.ok(new SucessDTO("Usuário removido com sucesso"));
    }

    @Test
    void deveSalvarApostador() {
        var response = assertDoesNotThrow(() -> userController.deleteUser(1));
        assertNotNull(response);
        assertEquals(statusCriadoComSucesso, response);
    }

}

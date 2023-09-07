package br.edu.ifsc.chamados.services;

import br.edu.ifsc.chamados.configs.exceptions.DefaultException;
import br.edu.ifsc.chamados.configs.exceptions.RecordNotFound2Exception;
import br.edu.ifsc.chamados.configs.exceptions.RegisterUser2Exception;
import br.edu.ifsc.chamados.dto.SucessDTO;
import br.edu.ifsc.chamados.enums.Role;
import br.edu.ifsc.chamados.models.user.User;
import br.edu.ifsc.chamados.repositories.UserRepository;
import br.edu.ifsc.chamados.requests.RegisterRequest;
import br.edu.ifsc.chamados.response.user.UserResponse;
import br.edu.ifsc.chamados.services.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindUsers() {
        User user1 = new User();
        user1.setId(1);
        user1.setEmail("user1@example.com");
        user1.setFirstname("John");
        user1.setLastname("Doe");
        user1.setPhone(Long.parseLong("23456789"));
        user1.setRole(Role.USER);
        user1.setActive(true);
        user1.setDataCriacao(LocalDateTime.now());

        User user2 = new User();
        user2.setId(2);
        user2.setEmail("user2@example.com");
        user2.setFirstname("Jane");
        user2.setLastname("Doe");
        user2.setPhone(Long.parseLong("98765413321"));
        user2.setRole(Role.ADMIN);
        user2.setActive(false);
        user2.setDataCriacao(LocalDateTime.now());

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<UserResponse> userResponses = userService.findUsers();

        assertEquals(2, userResponses.size());
        assertEquals("user1@example.com", userResponses.get(0).getEmail());
        assertEquals("user2@example.com", userResponses.get(1).getEmail());
    }

    @Test
    void testSaveUser() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstname("John");
        registerRequest.setLastname("Doe");
        registerRequest.setEmail("user1@example.com");
        registerRequest.setPassword("password123");
        registerRequest.setPhone(Long.parseLong("123456789"));

        User savedUser = new User();
        savedUser.setId(1);
        savedUser.setEmail("user1@example.com");
        savedUser.setFirstname("John");
        savedUser.setLastname("Doe");
        savedUser.setPhone(Long.parseLong("123456789"));
        savedUser.setRole(Role.USER);
        savedUser.setActive(false);
        savedUser.setDataCriacao(LocalDateTime.now());

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User result = userService.saveUser(registerRequest);

        assertEquals(1, result.getId());
        assertEquals("user1@example.com", result.getEmail());
        assertEquals("John", result.getFirstname());
        assertEquals("Doe", result.getLastname());
        assertEquals(Role.USER, result.getRole());
        assertFalse(result.getActive());
    }

    @Test
    void testUpdateUser() throws DefaultException {

        Integer userId = 1;
        RegisterRequest userUpdt = new RegisterRequest();
        userUpdt.setFirstname("Updated Firstname");
        userUpdt.setLastname("Updated Lastname");
        userUpdt.setEmail("updated@example.com");
        userUpdt.setPassword("updatedPassword123");

        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setEmail("user@example.com");
        existingUser.setFirstname("Firstname");
        existingUser.setLastname("Lastname");
        existingUser.setPhone(Long.parseLong("123456789"));
        existingUser.setRole(Role.USER);
        existingUser.setActive(true);
        existingUser.setDataCriacao(LocalDateTime.now());

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        UserResponse updatedUser = userService.updateUser(userUpdt, userId);

        assertEquals(userId, updatedUser.getId());
        assertEquals("updated@example.com", updatedUser.getEmail());
        assertEquals("Updated Firstname", updatedUser.getNome());
        assertEquals("Updated Lastname", updatedUser.getSobrenome());
    }

    @Test
    void testDeleteUser() throws RecordNotFound2Exception {

        Integer userId = 1;
        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setEmail("user@example.com");
        existingUser.setFirstname("Firstname");
        existingUser.setLastname("Lastname");
        existingUser.setPhone(Long.parseLong("123456789"));
        existingUser.setRole(Role.USER);
        existingUser.setActive(true);
        existingUser.setDataCriacao(LocalDateTime.now());

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        userService.deleteUser(userId);

        verify(userRepository, times(1)).delete(existingUser);
    }

    @Test
    void testFindUserById() throws RecordNotFound2Exception {
        Integer userId = 1;
        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setEmail("user@example.com");
        existingUser.setFirstname("Firstname");
        existingUser.setLastname("Lastname");
        existingUser.setPhone(Long.parseLong("123456789"));
        existingUser.setRole(Role.USER);
        existingUser.setActive(true);
        existingUser.setDataCriacao(LocalDateTime.now());

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        User result = userService.findUserById(userId);

        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals("user@example.com", result.getEmail());
        assertEquals("Firstname", result.getFirstname());
        assertEquals("Lastname", result.getLastname());
    }

    @Test
    void testFindUserByEmail() throws RecordNotFound2Exception {
        String email = "user@example.com";
        User existingUser = new User();
        existingUser.setId(1);
        existingUser.setEmail(email);
        existingUser.setFirstname("Firstname");
        existingUser.setLastname("Lastname");
        existingUser.setPhone(Long.parseLong("123456789"));
        existingUser.setRole(Role.USER);
        existingUser.setActive(true);
        existingUser.setDataCriacao(LocalDateTime.now());

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(existingUser));

        User result = userService.findUserByEmail(email);

        assertNotNull(result);
        assertEquals(email, result.getEmail());
        assertEquals("Firstname", result.getFirstname());
        assertEquals("Lastname", result.getLastname());
    }

    @Test
    void testAtivaUsuario() throws RecordNotFound2Exception {

        String email = "user@example.com";
        User existingUser = new User();
        existingUser.setId(1);
        existingUser.setEmail(email);
        existingUser.setFirstname("Firstname");
        existingUser.setLastname("Lastname");
        existingUser.setPhone(Long.parseLong("123456789"));
        existingUser.setRole(Role.USER);
        existingUser.setActive(true);
        existingUser.setDataCriacao(LocalDateTime.now());

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(existingUser));

        SucessDTO result = userService.ativaUsuario(email);

        assertNotNull(result);
        assertEquals("Solicitação realizada com sucesso.", result.getMessage());
        assertFalse(existingUser.getActive());
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    void testSaveUserWithExistingEmail() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstname("John");
        registerRequest.setLastname("Doe");
        registerRequest.setEmail("existing@example.com");
        registerRequest.setPassword("password123");
        registerRequest.setPhone(Long.parseLong("123456789"));

        when(userRepository.findByEmail(registerRequest.getEmail())).thenReturn(Optional.of(new User()));

        assertThrows(RegisterUser2Exception.class, () -> userService.saveUser(registerRequest));
    }

    @Test
    void testSaveUserWithExistingPhone() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstname("John");
        registerRequest.setLastname("Doe");
        registerRequest.setEmail("user@example.com");
        registerRequest.setPassword("password123");
        registerRequest.setPhone(Long.parseLong("123456789"));

        when(userRepository.findByPhone(registerRequest.getPhone())).thenReturn(Optional.of(new User()));

        assertThrows(RegisterUser2Exception.class, () -> userService.saveUser(registerRequest));
    }
}


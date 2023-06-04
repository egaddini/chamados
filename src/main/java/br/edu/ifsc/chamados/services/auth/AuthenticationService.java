package br.edu.ifsc.chamados.services.auth;

import br.edu.ifsc.chamados.api.models.user.IUser;
import br.edu.ifsc.chamados.configs.exceptions.InactiveUser2Exception;
import br.edu.ifsc.chamados.configs.exceptions.UnauthorizedException;
import br.edu.ifsc.chamados.configs.security.JwtService;
import br.edu.ifsc.chamados.dto.SucessDTO;
import br.edu.ifsc.chamados.enums.Role;
import br.edu.ifsc.chamados.enums.TokenType;
import br.edu.ifsc.chamados.models.auth.Token;
import br.edu.ifsc.chamados.models.user.User;
import br.edu.ifsc.chamados.repositories.TokenRepository;
import br.edu.ifsc.chamados.repositories.UserRepository;
import br.edu.ifsc.chamados.requests.AuthenticationRequest;
import br.edu.ifsc.chamados.requests.RegisterRequest;
import br.edu.ifsc.chamados.response.auth.AuthenticationResponse;
import br.edu.ifsc.chamados.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public SucessDTO register(RegisterRequest request) throws Exception {
        userService.saveUser(request);
        return new SucessDTO("Solicitação realizada com sucesso.");
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {

        var user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new UnauthorizedException());

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );

        repository.findByEmailAndActiveIs(request.getEmail(), true).orElseThrow(() -> new InactiveUser2Exception(request.getEmail()));

        var jwtToken = jwtService.generateToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return buildAuthResponse(user, jwtToken);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    private AuthenticationResponse buildAuthResponse(User user, String token) {
        return AuthenticationResponse.builder()
            .id(user.getId())
            .nome(user.getFirstname())
            .sobrenome(user.getLastname())
            .email(user.getEmail())
            .role(user.getRole())
            .token(token)
            .habilitado(user.getActive())
            .dataCriacao(user.getDataCriacao())
            .telefone(user.getPhone())
            .build();
    }

}
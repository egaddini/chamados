package br.edu.ifsc.chamados.api.controllers.auth;

import br.edu.ifsc.chamados.dto.SucessDTO;
import br.edu.ifsc.chamados.requests.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationControllerV1 {

    public static final String BASE_PATH = "/api/auth/v1";
    public static final String REGISTER_PATH = "/register";
    public static final String AUTHENTICATE_PATH = "/authenticate";

    ResponseEntity<SucessDTO> register(@RequestBody RegisterRequest request) throws Exception;
    //ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request);
}

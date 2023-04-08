package br.edu.ifsc.chamados.api.controllers.auth;

import br.edu.ifsc.chamados.requests.AuthenticationRequest;
import br.edu.ifsc.chamados.requests.RegisterRequest;
import br.edu.ifsc.chamados.response.auth.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationControllerV1 {

    public static final String BASE_PATH = "/api/v1/auth";
    public static final String REGISTER_PATH = "/register";
    public static final String AUTHENTICATE_PATH = "/authenticate";

    ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) throws Exception;
    //ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request);
}

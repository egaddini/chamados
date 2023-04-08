package br.edu.ifsc.chamados.controllers;

import br.edu.ifsc.chamados.api.controllers.auth.AuthenticationControllerV1;
import br.edu.ifsc.chamados.requests.AuthenticationRequest;
import br.edu.ifsc.chamados.requests.RegisterRequest;
import br.edu.ifsc.chamados.response.auth.AuthenticationResponse;
import br.edu.ifsc.chamados.services.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AuthenticationControllerV1.BASE_PATH)
public class AuthenticationControllerV1Impl implements AuthenticationControllerV1 {
    private final AuthenticationService service;
    @Override
    @PostMapping(REGISTER_PATH)
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) throws Exception {
        return ResponseEntity.ok(service.register(request));
    }
    //@Override
    @PostMapping(AUTHENTICATE_PATH)
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

}

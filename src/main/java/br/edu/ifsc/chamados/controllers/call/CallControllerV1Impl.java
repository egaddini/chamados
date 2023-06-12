package br.edu.ifsc.chamados.controllers;

import br.edu.ifsc.chamados.api.controllers.call.CallControllerV1;
import br.edu.ifsc.chamados.api.controllers.user.UserControllerV1;
import br.edu.ifsc.chamados.dto.SucessDTO;
import br.edu.ifsc.chamados.requests.CallRequest;
import br.edu.ifsc.chamados.requests.RegisterRequest;
import br.edu.ifsc.chamados.services.call.CallService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(CallControllerV1.BASE_PATH)
public class CallControllerV1Impl {

    @Autowired
    private CallService service;

    @PostMapping()
    public ResponseEntity<SucessDTO> register(@RequestBody CallRequest request) throws Exception {
        return ResponseEntity.ok(service.register(request));
    }

}

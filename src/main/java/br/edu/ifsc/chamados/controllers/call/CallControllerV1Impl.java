package br.edu.ifsc.chamados.controllers.call;

import br.edu.ifsc.chamados.api.controllers.call.CallControllerV1;
import br.edu.ifsc.chamados.dto.SucessDTO;
import br.edu.ifsc.chamados.requests.CallRequest;
import br.edu.ifsc.chamados.response.call.CallResponse;
import br.edu.ifsc.chamados.services.call.CallService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(CallControllerV1.BASE_PATH)
public class CallControllerV1Impl implements CallControllerV1 {

    @Autowired
    private CallService service;

    @GetMapping()
    public List<CallResponse> findAll() throws Exception {
        return service.findAll(null);
    }
    @GetMapping(EMAIL_PATH)
    public List<CallResponse> findAllByEmail(@PathVariable("email") String email) throws Exception {
        return service.findAll(email);
    }

    @PostMapping()
    public ResponseEntity<SucessDTO> register(@RequestBody CallRequest request) throws Exception {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping(CANCELA_PATH)
    public ResponseEntity<SucessDTO> cancelar(@PathVariable("id") Integer id, @RequestBody String motivo) throws Exception {
        return ResponseEntity.ok(service.cancela(id, motivo));
    }

}

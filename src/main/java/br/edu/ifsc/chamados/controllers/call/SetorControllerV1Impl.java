package br.edu.ifsc.chamados.controllers.call;

import br.edu.ifsc.chamados.api.controllers.call.SetorControllerV1;
import br.edu.ifsc.chamados.api.controllers.user.UserControllerV1;
import br.edu.ifsc.chamados.configs.exceptions.DefaultException;
import br.edu.ifsc.chamados.configs.exceptions.RecordNotFound2Exception;
import br.edu.ifsc.chamados.dto.SucessDTO;
import br.edu.ifsc.chamados.models.call.Setor;
import br.edu.ifsc.chamados.models.user.User;
import br.edu.ifsc.chamados.requests.RegisterRequest;
import br.edu.ifsc.chamados.response.user.UserResponse;
import br.edu.ifsc.chamados.services.call.SetorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping(SetorControllerV1.BASE_PATH)
public class SetorControllerV1Impl implements SetorControllerV1 {
    @Autowired
    private SetorService svc;

    @GetMapping
    public List<Setor> findAll(){
        return svc.findAll();
    }


    @PostMapping()
    public ResponseEntity<SucessDTO> save(@RequestBody Setor request) throws Exception {
        return ResponseEntity.ok(svc.save(request));
    }




}

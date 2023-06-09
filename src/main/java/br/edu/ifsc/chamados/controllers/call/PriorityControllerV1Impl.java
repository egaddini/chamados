package br.edu.ifsc.chamados.controllers.call;

import br.edu.ifsc.chamados.api.controllers.call.PriorityControllerV1;
import br.edu.ifsc.chamados.dto.SucessDTO;
import br.edu.ifsc.chamados.models.call.Prioritised;
import br.edu.ifsc.chamados.services.call.PriorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(PriorityControllerV1.BASE_PATH)
public class PriorityControllerV1Impl implements PriorityControllerV1{

    @Autowired
    private PriorityService svc;

    @PostMapping()
    public ResponseEntity<SucessDTO> save(@RequestBody Prioritised request) throws Exception {
        return ResponseEntity.ok(svc.save(request));
    }


}

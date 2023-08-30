package br.edu.ifsc.chamados.controllers.call;

import br.edu.ifsc.chamados.api.controllers.call.CallTypeControllerV1;
import br.edu.ifsc.chamados.dto.SucessDTO;
import br.edu.ifsc.chamados.models.call.CallType;
import br.edu.ifsc.chamados.requests.CallTypeRequest;
import br.edu.ifsc.chamados.services.call.CallTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(CallTypeControllerV1.BASE_PATH)
public class CallTypeControllerV1Impl implements CallTypeControllerV1 {

    @Autowired
    private CallTypeService svc;

    @GetMapping()
    public List<CallType> findAll() throws Exception {
        return svc.findAll();
    }

    @PostMapping()
    public ResponseEntity<SucessDTO> save(@RequestBody CallTypeRequest request) throws Exception {
        return ResponseEntity.ok(svc.save(request));
    }

    @DeleteMapping(ID_PATH)
    public ResponseEntity<SucessDTO> delete(@PathVariable("id") Integer id) throws Exception {
        return ResponseEntity.ok(svc.delete(id));
    }

}

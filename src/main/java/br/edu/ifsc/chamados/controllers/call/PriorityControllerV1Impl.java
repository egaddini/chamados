package br.edu.ifsc.chamados.controllers.call;

import br.edu.ifsc.chamados.api.controllers.call.PriorityControllerV1;
import br.edu.ifsc.chamados.dto.SucessDTO;
import br.edu.ifsc.chamados.models.call.Prioritised;
import br.edu.ifsc.chamados.services.call.PriorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(PriorityControllerV1.BASE_PATH)
public class PriorityControllerV1Impl implements PriorityControllerV1{

    @Autowired
    private PriorityService svc;

    @GetMapping
    public List<Prioritised> findAll(){
        return svc.findAll();
    }

    @GetMapping(FREE_WEIGHTS_PATH)
    public List<Integer> findFreeWeights() {
        return svc.findFreeWeights();
    }

    @PostMapping()
    public ResponseEntity<SucessDTO> save(@RequestBody Prioritised request) throws Exception {
        return ResponseEntity.ok(svc.save(request));
    }

    @DeleteMapping(ID_PATH)
    public ResponseEntity<SucessDTO> delete(@PathVariable("id") Integer id) throws Exception {
        return ResponseEntity.ok(svc.delete(id));
    }


}

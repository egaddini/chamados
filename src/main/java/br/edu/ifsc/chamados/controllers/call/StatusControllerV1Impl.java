package br.edu.ifsc.chamados.controllers.call;

import br.edu.ifsc.chamados.api.controllers.call.SectorControllerV1;
import br.edu.ifsc.chamados.api.controllers.call.StatusControllerV1;
import br.edu.ifsc.chamados.configs.exceptions.RecordNotFound2Exception;
import br.edu.ifsc.chamados.dto.SucessDTO;
import br.edu.ifsc.chamados.models.call.Sector;
import br.edu.ifsc.chamados.models.call.Status;
import br.edu.ifsc.chamados.requests.StatusRequest;
import br.edu.ifsc.chamados.services.call.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(StatusControllerV1.BASE_PATH)
public class StatusControllerV1Impl implements StatusControllerV1 {

    @Autowired
    private StatusService svc;

    @GetMapping
    public List<Status> findAll(){return svc.findAll();
    }

    @GetMapping(NAME_PATH)
    public Status findByName(@RequestParam String name) throws RecordNotFound2Exception {
        return svc.findByName(name);
    }

    @GetMapping(FREE_WEIGHTS_PATH)
    public List<Integer> findFreeWeights() {
        return svc.findFreeWeights();
    }

    @PostMapping()
    public ResponseEntity<SucessDTO> save(@RequestBody StatusRequest request) throws Exception {
        return ResponseEntity.ok(svc.save(request));
    }

    @DeleteMapping(ID_PATH)
    public ResponseEntity<SucessDTO> delete(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok(svc.delete(id));
    }

    @GetMapping(ID_PATH)
    public Status findById(@PathVariable("id") Long id) throws Exception {
        return svc.findById(id);
    }



}

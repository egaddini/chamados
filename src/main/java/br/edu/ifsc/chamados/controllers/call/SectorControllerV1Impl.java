package br.edu.ifsc.chamados.controllers.call;

import br.edu.ifsc.chamados.api.controllers.call.SectorControllerV1;
import br.edu.ifsc.chamados.dto.SucessDTO;
import br.edu.ifsc.chamados.models.call.Sector;
import br.edu.ifsc.chamados.services.call.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping(SectorControllerV1.BASE_PATH)
public class SectorControllerV1Impl implements SectorControllerV1 {

    @Autowired
    private SectorService svc;

    @GetMapping
    public List<Sector> findAll(){
        return svc.findAll();
    }


    @PostMapping()
    public ResponseEntity<SucessDTO> save(@RequestBody Sector request) throws Exception {
        return ResponseEntity.ok(svc.save(request));
    }

    @DeleteMapping(ID_PATH)
    public ResponseEntity<SucessDTO> delete(@PathVariable("id") Integer id) throws Exception {
        return ResponseEntity.ok(svc.delete(id));
    }

}

package br.edu.ifsc.chamados.controllers.call;

import br.edu.ifsc.chamados.api.controllers.call.CallCategoryControllerV1;
import br.edu.ifsc.chamados.dto.SucessDTO;
import br.edu.ifsc.chamados.models.call.CallCategory;
import br.edu.ifsc.chamados.requests.CallCategoryFilter;
import br.edu.ifsc.chamados.requests.CallTypeRequest;
import br.edu.ifsc.chamados.services.call.CallTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(CallCategoryControllerV1.BASE_PATH)
public class CallCategoryControllerV1Impl implements CallCategoryControllerV1 {

    @Autowired
    private CallTypeService svc;

    @GetMapping
    public List<CallCategory> findAll(CallCategoryFilter filter) throws Exception {
        return svc.findAll(filter);
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

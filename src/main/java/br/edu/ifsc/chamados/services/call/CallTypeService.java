package br.edu.ifsc.chamados.services.call;

import br.edu.ifsc.chamados.configs.exceptions.RecordNotFound2Exception;
import br.edu.ifsc.chamados.configs.exceptions.RegisterUser2Exception;
import br.edu.ifsc.chamados.dto.SucessDTO;
import br.edu.ifsc.chamados.models.call.CallCategory;
import br.edu.ifsc.chamados.repositories.CallCategoryRepository;
import br.edu.ifsc.chamados.requests.CallTypeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallTypeService {

    @Autowired
    private CallCategoryRepository repository;
    @Autowired
    private SectorService setorSvc;
    @Autowired
    private PriorityService prioritySvc;

    public CallCategory findUserById(Integer id) throws RecordNotFound2Exception {
        return repository.findById(id).orElseThrow(() -> new RecordNotFound2Exception(id.toString()));
    }

    public SucessDTO save(CallTypeRequest request) throws Exception {

        if (repository.existsByTitle(request.getTitle())) throw new RegisterUser2Exception("Titulo", request.getTitle());
        if (request.getDescription().isBlank()) throw new Exception("erro");
        CallCategory callType = new CallCategory(null, request.getTitle(), setorSvc.findById(request.getSectorId()), prioritySvc.findById(request.getPriorityId()),request.getDescription());
        repository.save(callType);
        return new SucessDTO("Solicitação realizada com sucesso.");
    }

    public List<CallCategory> findAll() {
        return repository.findAll();
    }

    public SucessDTO delete(Integer id) throws RegisterUser2Exception {
        repository.deleteById(id);
        return new SucessDTO("Solicitação realizada com sucesso.");
    }

}

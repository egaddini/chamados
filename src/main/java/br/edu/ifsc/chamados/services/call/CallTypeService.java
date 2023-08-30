package br.edu.ifsc.chamados.services.call;

import br.edu.ifsc.chamados.configs.exceptions.RecordNotFound2Exception;
import br.edu.ifsc.chamados.configs.exceptions.RegisterUser2Exception;
import br.edu.ifsc.chamados.dto.SucessDTO;
import br.edu.ifsc.chamados.models.call.CallType;
import br.edu.ifsc.chamados.repositories.CallTypeRepository;
import br.edu.ifsc.chamados.requests.CallTypeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallTypeService {

    @Autowired
    private CallTypeRepository repository;
    @Autowired
    private SectorService setorSvc;
    @Autowired
    private PriorityService prioritySvc;

    public CallType findUserById(Integer id) throws RecordNotFound2Exception {
        return repository.findById(id).orElseThrow(() -> new RecordNotFound2Exception(id.toString()));
    }

    public SucessDTO save(CallTypeRequest request) throws Exception {

        if (repository.existsByTitulo(request.getTitulo())) throw new RegisterUser2Exception("Titulo", request.getTitulo());
        if (request.getDescricao().isBlank()) throw new Exception("erro");
        CallType callType = new CallType(null, request.getTitulo(), setorSvc.findById(request.getSetorId()), prioritySvc.findById(request.getPrioridadeId()),request.getDescricao());
        repository.save(callType);
        return new SucessDTO("Solicitação realizada com sucesso.");
    }

    public List<CallType> findAll() {
        return repository.findAll();
    }

    public SucessDTO delete(Integer id) throws RegisterUser2Exception {
        repository.deleteById(id);
        return new SucessDTO("Solicitação realizada com sucesso.");
    }

}

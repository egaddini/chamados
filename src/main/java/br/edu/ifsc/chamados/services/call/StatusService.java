package br.edu.ifsc.chamados.services.call;

import br.edu.ifsc.chamados.configs.exceptions.RecordNotFound2Exception;
import br.edu.ifsc.chamados.configs.exceptions.RegisterUser2Exception;
import br.edu.ifsc.chamados.dto.SucessDTO;
import br.edu.ifsc.chamados.models.call.Call;
import br.edu.ifsc.chamados.models.call.CallHistoric;
import br.edu.ifsc.chamados.models.call.CallStatus;
import br.edu.ifsc.chamados.repositories.StatusRepository;
import br.edu.ifsc.chamados.requests.StatusRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatusService {

    @Autowired
    private StatusRepository repository;
    @Autowired
    private CallService callService;
    @Autowired
    private HistoricService historicService;

    public List<CallStatus> findAll() {
        return repository.findAll();
    }

    public CallStatus findById(Long id) throws RecordNotFound2Exception {
        return repository.findById(id).orElseThrow(() -> new RecordNotFound2Exception(id.toString()));
    }

    public CallStatus findByName(String name) throws RecordNotFound2Exception {
        return repository.findByName(name).orElseThrow(() -> new RecordNotFound2Exception(name.toString()));
    }

    public CallStatus findByWeight(Integer weight) throws RecordNotFound2Exception {
        return repository.findByWeight(weight).orElseThrow(() -> new RecordNotFound2Exception(weight.toString()));
    }

    public SucessDTO save(StatusRequest request) throws RegisterUser2Exception {
        if (repository.existsByName(request.getName())) throw new RegisterUser2Exception("Nome", request.getName());
        if (repository.existsByWeight(request.getWeight())) throw new RegisterUser2Exception("Nome", request.getName());
        repository.save(new CallStatus(null, request.getName(), request.getDescription(), request.getWeight(), request.isNotify(), null));
        return new SucessDTO("Solicitação realizada com sucesso.");
    }

    public SucessDTO delete(Long id) throws RegisterUser2Exception {
        repository.deleteById(id);
        return new SucessDTO("Solicitação realizada com sucesso.");
    }
    public List<Integer> findFreeWeights() {
        return Arrays.asList(1,2,3,4,5,6,7,8,9,10).stream().filter(i -> !repository.findAllBy().stream()
             .map(j -> j.getWeight()).collect(Collectors.toList()).contains(i)).collect(Collectors.toList());
    }

    public CallStatus setStatus(Integer callID, Integer statusID) throws RecordNotFound2Exception {
        Call call = callService.findById(callID);
        CallStatus status = findByWeight(statusID);
        String message = (statusID == 10) ? "Encerrou o chamado" : String.format("Alterou o status de %s para %s", call.getStatus().getName(), status.getName());
        CallHistoric historic = historicService.saveHistoric((statusID == 10) ? call.getRequester().getEmail() : "Responsável", message, call);
        List<CallHistoric> savedHistoric = call.getHistoric();
        savedHistoric.add(historic);
        call.setHistoric(savedHistoric);
        call.setStatus(status);
        call.setLastUpdateDT(LocalDateTime.now());
        callService.register(call);
        return status;
    }

}

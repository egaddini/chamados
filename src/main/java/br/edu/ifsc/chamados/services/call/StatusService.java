package br.edu.ifsc.chamados.services.call;

import br.edu.ifsc.chamados.configs.exceptions.RecordNotFound2Exception;
import br.edu.ifsc.chamados.configs.exceptions.RegisterUser2Exception;
import br.edu.ifsc.chamados.dto.SucessDTO;
import br.edu.ifsc.chamados.models.call.Sector;
import br.edu.ifsc.chamados.models.call.Status;
import br.edu.ifsc.chamados.repositories.StatusRepository;
import br.edu.ifsc.chamados.requests.StatusRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatusService {

    @Autowired
    private StatusRepository repository;

    public List<Status> findAll() {
        return repository.findAll();
    }

    public Status findById(Long id) throws RecordNotFound2Exception {
        return repository.findById(id).orElseThrow(() -> new RecordNotFound2Exception(id.toString()));
    }

    public Status findByName(String name) throws RecordNotFound2Exception {
        return repository.findByName(name).orElseThrow(() -> new RecordNotFound2Exception(name.toString()));
    }

    public SucessDTO save(StatusRequest request) throws RegisterUser2Exception {
        if (repository.existsByName(request.getName())) throw new RegisterUser2Exception("Nome", request.getName());
        if (repository.existsByWeight(request.getWeight())) throw new RegisterUser2Exception("Nome", request.getName());
        repository.save(new Status(null, request.getName(), request.getDescription(), request.getWeight(), request.isNotify()));
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


}

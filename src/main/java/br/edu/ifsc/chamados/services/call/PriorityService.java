package br.edu.ifsc.chamados.services.call;


import br.edu.ifsc.chamados.configs.exceptions.RecordNotFound2Exception;
import br.edu.ifsc.chamados.configs.exceptions.RegisterUser2Exception;
import br.edu.ifsc.chamados.dto.SucessDTO;
import br.edu.ifsc.chamados.models.call.CallPriority;
import br.edu.ifsc.chamados.repositories.PriorityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PriorityService {

    @Autowired
    private PriorityRepository repository;

    public CallPriority findById(Integer id) throws RecordNotFound2Exception {
        return repository.findById(id).orElseThrow(() -> new RecordNotFound2Exception("Prioridade"));
    }

    public SucessDTO save(CallPriority priority) throws Exception {
        if (repository.existsByName(priority.getName())) throw new RegisterUser2Exception("Nome", priority.getName());
        repository.save(priority);
        return new SucessDTO("Solicitação realizada com sucesso.");
    }

    public List<CallPriority> findAll() {
        return repository.findAll();
    }

    public List<Integer> findFreeWeights() {
        return Arrays.asList(1,2,3,4,5,6,7,8,9,10).stream().filter(i -> !repository.findAllBy().stream()
                .map(j -> j.getWeight()).collect(Collectors.toList()).contains(i)).collect(Collectors.toList());
    }

    public SucessDTO delete(Integer id) throws RegisterUser2Exception {
        repository.deleteById(id);
        return new SucessDTO("Solicitação realizada com sucesso.");
    }


}

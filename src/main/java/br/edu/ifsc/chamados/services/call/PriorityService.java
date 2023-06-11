package br.edu.ifsc.chamados.services.call;


import br.edu.ifsc.chamados.configs.exceptions.RecordNotFound2Exception;
import br.edu.ifsc.chamados.configs.exceptions.RegisterUser2Exception;
import br.edu.ifsc.chamados.dto.SucessDTO;
import br.edu.ifsc.chamados.models.call.Prioritised;
import br.edu.ifsc.chamados.models.call.Setor;
import br.edu.ifsc.chamados.repositories.PriorityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PriorityService {

    @Autowired
    private PriorityRepository priorityRepo;

    public Prioritised findById(Integer id) throws RecordNotFound2Exception {
        return priorityRepo.findById(id).orElseThrow(() -> new RecordNotFound2Exception("Prioridade"));
    }

    public SucessDTO save(Prioritised priority) throws Exception {
        if (priorityRepo.existsByNome(priority.getNome())) throw new RegisterUser2Exception("Nome", priority.getNome());
        priorityRepo.save(priority);
        return new SucessDTO("Solicitação realizada com sucesso.");
    }

    public List<Prioritised> findAll() {
        return priorityRepo.findAll();
    }
}

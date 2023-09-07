package br.edu.ifsc.chamados.services.call;

import br.edu.ifsc.chamados.configs.exceptions.RecordNotFound2Exception;
import br.edu.ifsc.chamados.configs.exceptions.RegisterUser2Exception;
import br.edu.ifsc.chamados.dto.SucessDTO;
import br.edu.ifsc.chamados.models.call.CallSector;
import br.edu.ifsc.chamados.repositories.SectorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SectorService {

    @Autowired
    private SectorRepository repository;

    public CallSector findById(Integer id) throws RecordNotFound2Exception {
        return repository.findById(id).orElseThrow(() -> new RecordNotFound2Exception("Setor"));
    }

    public SucessDTO save(CallSector sector) throws Exception {
        if (repository.existsByAcronym(sector.getAcronym())) throw new RegisterUser2Exception("Sigla", sector.getAcronym());
        if (repository.existsByName(sector.getName())) throw new RegisterUser2Exception("Nome", sector.getName());
        repository.save(sector);
        return new SucessDTO("Solicitação realizada com sucesso.");
    }

    public List<CallSector> findAll() {
        return repository.findAll();
    }

    public SucessDTO delete(Integer id) throws RegisterUser2Exception {
        repository.deleteById(id);
        return new SucessDTO("Solicitação realizada com sucesso.");
    }
}

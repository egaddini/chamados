package br.edu.ifsc.chamados.services.call;

import br.edu.ifsc.chamados.configs.exceptions.RecordNotFound2Exception;
import br.edu.ifsc.chamados.configs.exceptions.RegisterUser2Exception;
import br.edu.ifsc.chamados.dto.SucessDTO;
import br.edu.ifsc.chamados.models.call.Sector;
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

    public Sector findById(Integer id) throws RecordNotFound2Exception {
        return repository.findById(id).orElseThrow(() -> new RecordNotFound2Exception("Setor"));
    }

    public SucessDTO save(Sector sector) throws Exception {
        if (repository.existsBySigla(sector.getSigla())) throw new RegisterUser2Exception("Sigla", sector.getSigla());
        if (repository.existsByNome(sector.getNome())) throw new RegisterUser2Exception("Nome", sector.getNome());
        repository.save(sector);
        return new SucessDTO("Solicitação realizada com sucesso.");
    }

    public List<Sector> findAll() {
        return repository.findAll();
    }

    public SucessDTO delete(Integer id) throws RegisterUser2Exception {
        repository.deleteById(id);
        return new SucessDTO("Solicitação realizada com sucesso.");
    }
}

package br.edu.ifsc.chamados.services.call;

import br.edu.ifsc.chamados.configs.exceptions.RecordNotFound2Exception;
import br.edu.ifsc.chamados.configs.exceptions.RegisterUser2Exception;
import br.edu.ifsc.chamados.dto.SucessDTO;
import br.edu.ifsc.chamados.models.call.Setor;
import br.edu.ifsc.chamados.repositories.SetorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SetorService {

    @Autowired
    private SetorRepository setorRepository;

    public Setor findById(Integer id) throws RecordNotFound2Exception {
        return setorRepository.findById(id).orElseThrow(() -> new RecordNotFound2Exception("Setor"));
    }

    public SucessDTO save(Setor setor) throws Exception {
        if (setorRepository.existsBySigla(setor.getSigla())) throw new RegisterUser2Exception("Sigla", setor.getSigla());
        if (setorRepository.existsByNome(setor.getNome())) throw new RegisterUser2Exception("Nome", setor.getNome());
        setorRepository.save(setor);
        return new SucessDTO("Solicitação realizada com sucesso.");
    }

    public List<Setor> findAll() {
        return setorRepository.findAll();
    }
}

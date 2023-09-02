package br.edu.ifsc.chamados.services.call;

import br.edu.ifsc.chamados.models.call.Call;
import br.edu.ifsc.chamados.models.call.Historic;
import br.edu.ifsc.chamados.repositories.HistoricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class HistoricService {

    @Autowired
    private HistoricRepository repository;

    public Historic saveHistoric(String user, String message, Call call) {
        return repository.save(new Historic(null, LocalDateTime.now(), user, message, call));
    }

}

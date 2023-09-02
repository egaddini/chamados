package br.edu.ifsc.chamados.services.call;

import br.edu.ifsc.chamados.models.call.Call;
import br.edu.ifsc.chamados.models.call.CallHistoric;
import br.edu.ifsc.chamados.repositories.HistoricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class HistoricService {

    @Autowired
    private HistoricRepository repository;

    public CallHistoric saveHistoric(String user, String message, Call call) {
        return repository.save(new CallHistoric(null, LocalDateTime.now(), user, message, call));
    }

}

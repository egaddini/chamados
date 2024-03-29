package br.edu.ifsc.chamados.repositories;

import br.edu.ifsc.chamados.models.call.CallHistoric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricRepository extends JpaRepository<CallHistoric, Integer> {

}

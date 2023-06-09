package br.edu.ifsc.chamados.repositories;

import br.edu.ifsc.chamados.models.call.Prioritised;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriorityRepository extends JpaRepository<Prioritised, Integer> {
    boolean existsByNome(String nome);
}

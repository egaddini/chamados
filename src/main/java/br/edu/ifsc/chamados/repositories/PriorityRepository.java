package br.edu.ifsc.chamados.repositories;

import br.edu.ifsc.chamados.models.call.Prioritised;
import br.edu.ifsc.chamados.projections.StatusWeightsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PriorityRepository extends JpaRepository<Prioritised, Integer> {
    boolean existsByNome(String nome);

    Collection<StatusWeightsProjection> findAllBy();
}

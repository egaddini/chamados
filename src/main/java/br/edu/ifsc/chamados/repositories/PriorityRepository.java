package br.edu.ifsc.chamados.repositories;

import br.edu.ifsc.chamados.models.call.CallPriority;
import br.edu.ifsc.chamados.projections.StatusWeightsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PriorityRepository extends JpaRepository<CallPriority, Integer> {
    boolean existsByName(String name);

    Collection<StatusWeightsProjection> findAllBy();
}

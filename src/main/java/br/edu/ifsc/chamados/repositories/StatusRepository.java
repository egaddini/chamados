package br.edu.ifsc.chamados.repositories;

import br.edu.ifsc.chamados.models.call.CallStatus;
import br.edu.ifsc.chamados.projections.StatusWeightsProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StatusRepository extends JpaRepository<CallStatus, Long> {
    boolean existsByWeight(int weight);

    boolean existsByName(String name);

    Optional<CallStatus> findByName(String name);

    Optional<CallStatus> findByWeight(int weight);

    List<StatusWeightsProjection> findAllBy();

}

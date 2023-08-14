package br.edu.ifsc.chamados.repositories;

import br.edu.ifsc.chamados.models.call.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Long> {
    boolean existsByWeight(int weight);

    boolean existsByName(String name);

    Optional<Status> findByName(String name);
}

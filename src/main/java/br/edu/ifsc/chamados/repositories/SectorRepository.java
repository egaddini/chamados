package br.edu.ifsc.chamados.repositories;

import br.edu.ifsc.chamados.models.call.CallSector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository extends JpaRepository<CallSector, Integer> {

    boolean existsByAcronym(String acronym);
    boolean existsByName(String name);

}

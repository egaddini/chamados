package br.edu.ifsc.chamados.repositories;

import br.edu.ifsc.chamados.models.call.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Integer> {

    boolean existsBySigla(String sigla);
    boolean existsByNome(String nome);

}

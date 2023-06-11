package br.edu.ifsc.chamados.repositories;

import br.edu.ifsc.chamados.models.call.CallType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallTypeRepository extends JpaRepository<CallType, Integer>{

    boolean existsByTitulo(String titulo);

}

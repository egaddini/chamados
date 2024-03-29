package br.edu.ifsc.chamados.repositories;

import br.edu.ifsc.chamados.models.call.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface CallRepository extends JpaRepository<Call, Integer>, JpaSpecificationExecutor<Call> {
    List<Call> findAllById(Long id);
    List<Call> findAllByRequester_email(String email);
    List<Call> findAllBySolver_email(String email);
}


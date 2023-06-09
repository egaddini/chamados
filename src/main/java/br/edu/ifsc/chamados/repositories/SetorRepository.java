package br.edu.ifsc.chamados.repositories;

import br.edu.ifsc.chamados.models.call.Setor;
import br.edu.ifsc.chamados.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetorRepository  extends JpaRepository<Setor, Integer> {

    boolean existsBySigla(String sigla);
    boolean existsByNome(String nome);

}

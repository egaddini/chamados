package br.edu.ifsc.chamados.repositories;

import br.edu.ifsc.chamados.models.call.Call;
import br.edu.ifsc.chamados.models.call.CallCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CallCategoryRepository extends JpaRepository<CallCategory, Integer>, JpaSpecificationExecutor<CallCategory> {

    boolean existsByTitle(String title);

}

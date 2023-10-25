package br.edu.ifsc.chamados.repositories;

import br.edu.ifsc.chamados.models.user.UserSector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSectorRepository extends JpaRepository<UserSector, Integer> {

    void deleteAllByUser_id(Integer userId);

}

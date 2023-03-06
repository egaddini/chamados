package br.edu.ifsc.chamados.repositories;

import br.edu.ifsc.chamados.enums.ERoleName;
import br.edu.ifsc.chamados.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    List<Role> findByRoleName(ERoleName RoleName);

}

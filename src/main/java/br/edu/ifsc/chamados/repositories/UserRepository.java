package br.edu.ifsc.chamados.repositories;

import br.edu.ifsc.chamados.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(Long phone);

    Optional<User> findByEmailAndActiveIs(String email, Boolean active);

}

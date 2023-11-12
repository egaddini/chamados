package br.edu.ifsc.chamados.repositories;

import br.edu.ifsc.chamados.models.call.CallRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallRatingRepository extends JpaRepository<CallRating, Integer> {


}

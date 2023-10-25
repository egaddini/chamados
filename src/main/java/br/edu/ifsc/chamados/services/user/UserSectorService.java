package br.edu.ifsc.chamados.services.user;

import br.edu.ifsc.chamados.models.user.UserSector;
import br.edu.ifsc.chamados.repositories.UserSectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserSectorService {

    @Autowired
    private UserSectorRepository repository;

    public Set<UserSector> saveUserSector(Set<UserSector> userSectors) {
        return repository.saveAll(userSectors).stream().collect(Collectors.toSet());
    }

    void removeUserSector(Integer userID) {
        repository.deleteAllByUser_id(userID);
    }

}

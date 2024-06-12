package br.com.fcoromoto.desafios.totvs.security.repository;

import br.com.fcoromoto.desafios.totvs.security.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}

package com.projeto.loginsystem.repository;

import com.projeto.loginsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // O Spring Data JPA cria a query SQL automaticamente pelo nome do método!
    Optional<User> findByEmail (String email);

    // Verifica se um e-mail já existe na tabela para evitar duplicados
    boolean existsByEmail (String email);

}

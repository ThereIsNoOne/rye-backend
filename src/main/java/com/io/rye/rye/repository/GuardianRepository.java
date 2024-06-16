package com.io.rye.rye.repository;

import com.io.rye.rye.entity.Guardian;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuardianRepository extends CrudRepository<Guardian, Integer> {
    @Query("SELECT g FROM Guardian g WHERE g.username = :username")
    Optional<Guardian> findByUsername(String username);

    Optional<Guardian> findGuardianById(int id);
}

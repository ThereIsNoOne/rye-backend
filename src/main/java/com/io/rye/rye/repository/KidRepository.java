package com.io.rye.rye.repository;

import com.io.rye.rye.entity.Kid;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KidRepository extends CrudRepository<Kid, Integer> {
    @Query("SELECT k FROM Kid k WHERE k.username = :username")
    Optional<Kid> findByUsername(String username);
}

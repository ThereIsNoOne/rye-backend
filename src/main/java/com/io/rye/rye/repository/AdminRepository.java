package com.io.rye.rye.repository;

import com.io.rye.rye.entity.Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer> {
    @Query("SELECT a FROM Admin a WHERE a.login = :login")
    Optional<Admin> findByLogin(String login);
}

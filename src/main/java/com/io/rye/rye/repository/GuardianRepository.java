package com.io.rye.rye.repository;

import com.io.rye.rye.entity.Guardian;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuardianRepository extends CrudRepository<Guardian, Integer> {
}

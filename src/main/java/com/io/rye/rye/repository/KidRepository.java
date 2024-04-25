package com.io.rye.rye.repository;

import com.io.rye.rye.entity.Kid;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KidRepository extends CrudRepository<Kid, Integer> {
}

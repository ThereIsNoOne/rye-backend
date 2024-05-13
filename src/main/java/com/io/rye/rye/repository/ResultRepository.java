package com.io.rye.rye.repository;


import com.io.rye.rye.entity.Result;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ResultRepository extends CrudRepository<Result, Integer> {
    @Query("SELECT r FROM Result r WHERE r.kid.id = :id")
    Iterable<Result> findByKid(String id);
}

package com.io.rye.rye.repository;


import com.io.rye.rye.entity.Result;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;


@Repository
public interface ResultRepository extends CrudRepository<Result, Integer> {
    @Query("SELECT r FROM Result r WHERE r.kid.id = :id")
    Iterable<Result> findByKid(String id);

    @Query("SELECT r FROM Result r WHERE r.kid.id = :id AND r.mode = :mode")
    Iterable<Result> findByKidAndMode(String id, String mode);

    @Query("SELECT r FROM Result r WHERE r.kid.id = :id AND r.date >= :startDate")
    Iterable<Result> findByKidAndDate(String id, Date startDate);

    @Query("SELECT r FROM Result r WHERE r.kid.id = :id AND r.mode = :mode AND r.date >= :startDate")
    Iterable<Result> findByKidAndModeAndDate(String id, String mode, Date startDate);
}

package com.io.rye.rye.repository;

import com.io.rye.rye.entity.MascotDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotDetailsRepository extends CrudRepository<MascotDetails, Integer> {
}

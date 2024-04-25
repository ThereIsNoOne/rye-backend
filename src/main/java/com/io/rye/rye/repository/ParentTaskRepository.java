package com.io.rye.rye.repository;

import com.io.rye.rye.entity.ParentTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentTaskRepository extends CrudRepository<ParentTask, Integer> {
}

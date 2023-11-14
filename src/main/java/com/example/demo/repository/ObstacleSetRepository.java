package com.example.demo.repository;


import com.example.demo.model.ObstacleSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ObstacleSetRepository extends JpaRepository<ObstacleSet, Long> {

    Optional<ObstacleSet> findByName(String name);

    void deleteAllByName(String name);
}

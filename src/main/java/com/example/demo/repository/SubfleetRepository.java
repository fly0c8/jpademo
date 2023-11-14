package com.example.demo.repository;


import com.example.demo.model.Subfleet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubfleetRepository extends JpaRepository<Subfleet, Long> {
}

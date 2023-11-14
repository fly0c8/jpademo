package com.example.demo.repository;


import com.example.demo.model.ObstacleSet;
import com.example.demo.model.ObstacleSetSubfleet;
import com.example.demo.model.Subfleet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObstacleSetSubfleetRepository extends JpaRepository<ObstacleSetSubfleet, Long> {
    void deleteAllBySubfleet(Subfleet sf);
    void deleteAllByObstacleSet(ObstacleSet os);
    void deleteAllByObstacleSetAndSubfleet(ObstacleSet os, Subfleet sf);


    @Modifying
    @Query("delete from ObstacleSetSubfleet ossf where ossf.subfleet.name=?1")
    void deleteAllBySubfleetName(String sfName);

    @Modifying
    @Query("delete from ObstacleSetSubfleet ossf where ossf.obstacleSet.name=?1")
    void deleteAllByObstacleSetName(String osName);


    List<ObstacleSetSubfleet> findAllByObstacleSet(ObstacleSet os);

    @Query("select ossf from ObstacleSetSubfleet ossf where ossf.obstacleSet.name=?1")
    List<ObstacleSetSubfleet> findAllByObstacleSetName(String osName);
}

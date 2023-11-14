package com.example.demo.service;

import com.example.demo.model.ObstacleSet;
import com.example.demo.model.Subfleet;
import com.example.demo.repository.ObstacleSetRepository;
import com.example.demo.repository.ObstacleSetSubfleetRepository;
import com.example.demo.repository.SubfleetRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObstacleService {
    @Autowired
    ObstacleSetRepository obstacleSetRepository;

    @Autowired
    SubfleetRepository subfleetRepository;

    @Autowired
    ObstacleSetSubfleetRepository obstacleSetSubfleetRepository;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void deleteAll() {
        obstacleSetSubfleetRepository.deleteAllInBatch();
        subfleetRepository.deleteAllInBatch();
        obstacleSetRepository.deleteAllInBatch();
    }
    @Transactional
    public List<Subfleet> saveSubfleets(List<Subfleet> subfleets) {
        return subfleetRepository.saveAll(subfleets);
    }

    @Transactional
    public List<ObstacleSet> saveObstacleSets(List<ObstacleSet> obstacleSets) {
        return obstacleSetRepository.saveAll(obstacleSets);
    }
    @Transactional
    public List<Subfleet> getSubfleetByObstacleSetName(String osName) {
        TypedQuery<Subfleet> q = em.createQuery("select sf from ObstacleSet os inner join ObstacleSetSubfleet ossf on ossf.obstacleSet = os inner join Subfleet sf on ossf.subfleet = sf where os.name = ?1", Subfleet.class);
        q.setParameter(1, osName);
        return q.getResultList();
    }
    @Transactional
    public void deleteObstacleSet(ObstacleSet os) {
        obstacleSetRepository.delete(os);
    }


}

//https://www.codejava.net/frameworks/hibernate/hibernate-many-to-many-association-with-extra-columns-in-join-table-example
//https://stackoverflow.com/questions/72144075/jpa-cascading-delete-fails-with-custom-delete-method

package com.example.demo;

import com.example.demo.model.ObstacleSet;
import com.example.demo.model.ObstacleSetSubfleet;
import com.example.demo.model.Subfleet;
import com.example.demo.repository.ObstacleSetRepository;
import com.example.demo.repository.ObstacleSetSubfleetRepository;
import com.example.demo.repository.SubfleetRepository;
import com.example.demo.service.ObstacleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Runner implements CommandLineRunner {
    private static Logger LOG = LoggerFactory.getLogger(Runner.class);

    @Autowired
    private ObstacleService obstacleService;

    @Override
    public void run(String... args) throws Exception {
        LOG.info("Hello world!");

        obstacleService.deleteAll();

        var sf37WE = Subfleet.builder().name("37WE").build();
        var sf3M8 = Subfleet.builder().name("3M8").build();
        var sf810G = Subfleet.builder().name("810G").build();
        var sf72G = Subfleet.builder().name("72G").build();
        var defaultOs = ObstacleSet.builder().name("default").build();
        var widebodies = ObstacleSet.builder().name("widebody").build();

        var subfleets = obstacleService.saveSubfleets(List.of(sf37WE, sf3M8, sf810G, sf72G));
        var obstaclesets = obstacleService.saveObstacleSets(List.of(defaultOs, widebodies));

        for (var sf : subfleets) {
            var os = (sf.getName().equals("810G") || sf.getName().equals("72G")) ? obstaclesets.get(1) : obstaclesets.get(0);
            sf.addObstacleSetSubfleet(ObstacleSetSubfleet.builder()
                            .obstacleSet(os)
                            .subfleet(sf)
                    .build());
        }
        obstacleService.saveSubfleets(subfleets);

        LOG.info("*** DEF ***");
        obstacleService.getSubfleetByObstacleSetName("default").forEach(sf -> LOG.info(sf.getName()));
        LOG.info("*** WB ***");
        obstacleService.getSubfleetByObstacleSetName("widebody").forEach(sf -> LOG.info(sf.getName()));

        obstacleService.deleteObstacleSet(obstaclesets.get(1));


        LOG.info("*** DEF ***");
        obstacleService.getSubfleetByObstacleSetName("default").forEach(sf -> LOG.info(sf.getName()));
        LOG.info("*** WB ***");
        obstacleService.getSubfleetByObstacleSetName("widebody").forEach(sf -> LOG.info(sf.getName()));


    }



}

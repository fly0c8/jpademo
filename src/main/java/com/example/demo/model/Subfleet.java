package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Subfleet {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "subfleet", cascade = CascadeType.ALL)
    private List<ObstacleSetSubfleet> obstacleSetSubfleets;

    public void addObstacleSetSubfleet(ObstacleSetSubfleet ossf) {
        if (obstacleSetSubfleets == null) obstacleSetSubfleets = new ArrayList<>();
        obstacleSetSubfleets.add(ossf);
    }
    public void removeObstacleSetSubfleet(ObstacleSetSubfleet ossf) {
        if (obstacleSetSubfleets != null) {
            obstacleSetSubfleets.remove(ossf);
        }
    }

}

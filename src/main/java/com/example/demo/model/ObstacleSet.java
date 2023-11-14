package com.example.demo.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ObstacleSet {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "obstacleSet", cascade = CascadeType.ALL)
    private Set<ObstacleSetSubfleet> obstacleSetSubfleets;

}

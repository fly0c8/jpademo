package com.example.demo.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ObstacleSetSubfleet {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subfleet_id")
    private Subfleet subfleet;

    @ManyToOne
    @JoinColumn(name = "obstacleset_id")
    private ObstacleSet obstacleSet;

}

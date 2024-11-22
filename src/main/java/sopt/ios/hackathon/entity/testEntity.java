package sopt.ios.hackathon.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "test")
public class testEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sopt")
    private String sopt;
}

package sopt.ios.hackathon.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "drink_limit", nullable = false)
    private double DrinkLimit;
    @Column(name = "name", nullable = false)
    private int DrinkCnt;

    public User(Long id, String name, double drinkLimit, int drinkCnt) {
        this.id = id;
        this.name = name;
        DrinkLimit = drinkLimit;
        DrinkCnt = drinkCnt;
    }

    public User(){

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getDrinkLimit() {
        return DrinkLimit;
    }
    public int getDrinkCnt() {
        return DrinkCnt;
    }
}




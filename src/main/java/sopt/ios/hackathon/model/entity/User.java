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
    @Column(name = "limit_drink_cnt", nullable = false)
    private int limitDrinkCnt;
    @Column(name = "drink_cnt", nullable = false)
    private int drinkCnt;

    @Column(name = "over_drink_cnt", nullable = false)
    private int overDrinkCnt;

    public User(Long id, String name, int limitDrinkCnt, int drinkCnt, int overDrinkCnt) {
        this.id = id;
        this.name = name;
        this.limitDrinkCnt = limitDrinkCnt;
        this.drinkCnt = drinkCnt;
        this.overDrinkCnt = overDrinkCnt;
    }

    public User(){

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLimitDrinkCnt() {
        return limitDrinkCnt;
    }
    public int getDrinkCnt() {
        return drinkCnt;
    }
    public int getOverDrinkCnt() {
        return overDrinkCnt;
    }

    public void setDrinkCnt(int drinkCnt){
        this.drinkCnt= drinkCnt;
    }
}




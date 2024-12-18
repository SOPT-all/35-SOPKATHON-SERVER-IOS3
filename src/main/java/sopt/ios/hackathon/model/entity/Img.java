package sopt.ios.hackathon.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "img")
public class Img {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "img_url", length = 500)
    private String imgUrl;

    public Img() {

    }

    public Img(final Long id, final String imgUrl) {
        this.id = id;
        this.imgUrl = imgUrl;
    }

    public Img(final String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}

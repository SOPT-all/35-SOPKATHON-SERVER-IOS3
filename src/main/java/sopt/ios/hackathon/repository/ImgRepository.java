package sopt.ios.hackathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sopt.ios.hackathon.model.entity.Img;

@Repository
public interface ImgRepository extends JpaRepository<Img, Long> {
}

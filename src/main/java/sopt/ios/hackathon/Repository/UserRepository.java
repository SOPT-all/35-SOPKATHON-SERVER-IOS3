package sopt.ios.hackathon.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sopt.ios.hackathon.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}

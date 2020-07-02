package ksaito.practice.jwt.repository;

import java.util.Optional;

import ksaito.practice.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByLoginId(String loginId);
}

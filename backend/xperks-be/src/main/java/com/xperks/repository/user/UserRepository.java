package com.xperks.repository.user;

import com.xperks.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {
    User getUserById(int id);
    Optional<User> findByEmailAddress(String emailAddress);
}

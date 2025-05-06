package chat.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import chat.chat.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

package az.spring.bankapplication.repository;

import az.spring.bankapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

    boolean existsUserByPinCode(String pinCode);

    boolean existsUserByEmail(String pinCode);

    Optional<User> findUserByEmail(String email);

}

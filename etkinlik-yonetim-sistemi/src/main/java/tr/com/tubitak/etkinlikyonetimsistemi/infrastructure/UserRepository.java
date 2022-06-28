package tr.com.tubitak.etkinlikyonetimsistemi.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.tubitak.etkinlikyonetimsistemi.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserNameAndPassword(String userName, String password);
}


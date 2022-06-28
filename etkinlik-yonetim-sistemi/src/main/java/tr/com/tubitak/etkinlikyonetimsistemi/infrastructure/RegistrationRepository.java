package tr.com.tubitak.etkinlikyonetimsistemi.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.tubitak.etkinlikyonetimsistemi.entity.Registration;
import tr.com.tubitak.etkinlikyonetimsistemi.entity.User;
import tr.com.tubitak.etkinlikyonetimsistemi.entity.value.RegistrationKey;

import java.util.List;
import java.util.UUID;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, RegistrationKey> {
    List<Registration> findAllByActivityId(UUID activityId);
    List<Registration> findAllByTcId(Long tcId);
}

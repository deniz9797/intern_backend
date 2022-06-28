package tr.com.tubitak.etkinlikyonetimsistemi.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.tubitak.etkinlikyonetimsistemi.entity.Activity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, UUID> {
    boolean existsByActivityId(UUID activityId);

    List<Activity> findActivitiesByStartDateAfter(LocalDateTime time);

}


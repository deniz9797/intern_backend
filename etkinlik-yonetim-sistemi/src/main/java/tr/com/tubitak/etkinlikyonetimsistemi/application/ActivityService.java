package tr.com.tubitak.etkinlikyonetimsistemi.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.tubitak.etkinlikyonetimsistemi.content.ActivityCreateRequestContent;
import tr.com.tubitak.etkinlikyonetimsistemi.content.ActivityUpdateRequestContent;
import tr.com.tubitak.etkinlikyonetimsistemi.entity.Activity;
import tr.com.tubitak.etkinlikyonetimsistemi.entity.User;
import tr.com.tubitak.etkinlikyonetimsistemi.infrastructure.ActivityRepository;
import tr.com.tubitak.etkinlikyonetimsistemi.util.ErrorType;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class ActivityService {
    @Autowired
    ActivityRepository activityRepository;

    public Activity addActivity(ActivityCreateRequestContent createRequestContent) {
        Activity activity = new Activity(createRequestContent.getActivityName(), createRequestContent.getStartDate(),
                createRequestContent.getEndDate(), createRequestContent.getQuota());

        return activityRepository.save(activity);
    }

    public Activity updateActivity(UUID id, ActivityUpdateRequestContent updateRequestContent) {
        Activity activity = activityRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException(ErrorType.ACTIVITY_NOT_FOUND));

        activity.update(updateRequestContent);

        return activityRepository.save(activity);
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public List<Activity> getNonExpiredActivities() {
        return activityRepository.findActivitiesByStartDateAfter(LocalDateTime.now());
    }

    public List<User> getRegisteredUser(UUID activityId) {
        Activity activity = activityRepository.findById(activityId).orElseThrow(()
                -> new EntityNotFoundException(ErrorType.ACTIVITY_NOT_FOUND));

        Map<Long, User> userMap = activity.getRegisteredCustomers();

        List<User> users = new ArrayList<>();
        userMap.forEach((id, user) -> users.add(user));

        return users;
    }
}


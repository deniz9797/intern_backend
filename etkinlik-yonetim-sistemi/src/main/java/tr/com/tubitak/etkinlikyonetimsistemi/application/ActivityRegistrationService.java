package tr.com.tubitak.etkinlikyonetimsistemi.application;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.tubitak.etkinlikyonetimsistemi.content.ActivityRegistrationRequestContent;
import tr.com.tubitak.etkinlikyonetimsistemi.entity.Activity;
import tr.com.tubitak.etkinlikyonetimsistemi.entity.Registration;
import tr.com.tubitak.etkinlikyonetimsistemi.entity.User;
import tr.com.tubitak.etkinlikyonetimsistemi.infrastructure.ActivityRepository;
import tr.com.tubitak.etkinlikyonetimsistemi.infrastructure.RegistrationRepository;
import tr.com.tubitak.etkinlikyonetimsistemi.infrastructure.UserRepository;
import tr.com.tubitak.etkinlikyonetimsistemi.response.ActivityCountChartValueResponse;
import tr.com.tubitak.etkinlikyonetimsistemi.util.ErrorType;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ActivityRegistrationService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    private final RegistrationRepository registrationRepository;

    public void registerUserToActivity(ActivityRegistrationRequestContent content) {
        User user = userRepository.findById(content.getCustomerId()).orElseThrow(() -> new EntityNotFoundException(ErrorType.USER_NOT_FOUND));
        Activity activity = activityRepository.findById(content.getActivityId()).orElseThrow(() -> new EntityNotFoundException(ErrorType.ACTIVITY_NOT_FOUND));
        activity.register(user);
        user.registered(activity);
        activityRepository.save(activity);
        userRepository.save(user);
        registrationRepository.save(new Registration(user, activity));
    }

    public List<Activity> getRegisteredActivities(long tcId) {
        User user = userRepository.findById(tcId).orElseThrow(() -> new EntityNotFoundException(ErrorType.USER_NOT_FOUND));
        Map<UUID, Activity> activityMap = user.getRegisteredActivities();
        Preconditions.checkArgument(!activityMap.isEmpty(), ErrorType.ACTIVITY_NOT_FOUND);

        List<Activity> activityList = new ArrayList<>();
        activityMap.forEach((id, activity) -> activityList.add(activity));

        return activityList;
    }

}

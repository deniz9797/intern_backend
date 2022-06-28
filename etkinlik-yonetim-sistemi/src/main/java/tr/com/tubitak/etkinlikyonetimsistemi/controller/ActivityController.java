package tr.com.tubitak.etkinlikyonetimsistemi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tr.com.tubitak.etkinlikyonetimsistemi.application.ActivityRegistrationService;
import tr.com.tubitak.etkinlikyonetimsistemi.application.ActivityService;
import tr.com.tubitak.etkinlikyonetimsistemi.application.ChartService;
import tr.com.tubitak.etkinlikyonetimsistemi.content.ActivityCreateRequestContent;
import tr.com.tubitak.etkinlikyonetimsistemi.content.ActivityRegistrationRequestContent;
import tr.com.tubitak.etkinlikyonetimsistemi.content.ActivityUpdateRequestContent;
import tr.com.tubitak.etkinlikyonetimsistemi.entity.Activity;
import tr.com.tubitak.etkinlikyonetimsistemi.infrastructure.ActivityRepository;
import tr.com.tubitak.etkinlikyonetimsistemi.response.*;
import tr.com.tubitak.etkinlikyonetimsistemi.util.Constants;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("activity")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class ActivityController {

    @NotEmpty(message = "Henüz etkinlik eklenmemiş")
    private final ActivityRepository activityRepository;

    private final ActivityService activityService;

    private final ActivityRegistrationService activityRegistrationService;

    private final ChartService chartService;

    //Kurum kullanıcısı endpointleri

    @GetMapping(path = "/allActivities")
    public List<ActivityQueryResponse> getAllActivities() {
        log.info("GetAllActivities requested.");

        return activityService.getAllActivities()
                .stream()
                .map(ActivityQueryResponse::new)
                .toList();
    }

    @GetMapping(path = "/getActivity")
    public ActivityQueryResponse getActivity(String activityId) {
        log.info("GetAllActivities requested.");

        return new ActivityQueryResponse(activityRepository.getById(UUID.fromString(activityId)));
    }

    @PostMapping(path = "/newActivity")
    public MessageResponse addActivity(@RequestBody ActivityCreateRequestContent activityCreateRequestContent) {
        log.info("AddActivity requested.");

        Activity activity = null;
        try {
            activity = activityService.addActivity(activityCreateRequestContent);
        } catch (Exception e) {
            e.printStackTrace();
            return MessageResponse.of(MessageType.ERROR, e.getMessage());
        }
        return MessageResponse.of(MessageType.SUCCESS, activity.toString());
    }

    @PostMapping(path = "/update/{id}")
    public MessageResponse updateActivity(@PathVariable String id, @RequestBody ActivityUpdateRequestContent content) {
        log.info("UpdateActivity requested.");

        Activity activity = null;
        try {
            UUID uuid = UUID.fromString(id);
            activity = activityService.updateActivity(uuid, content);
        } catch (Exception e) {
            e.printStackTrace();
            return MessageResponse.of(MessageType.ERROR, e.getMessage());
        }
        return MessageResponse.of(MessageType.SUCCESS, activity.toString());
    }

    @DeleteMapping(path = "/delete/{id}")
    public MessageResponse deleteActivity(@PathVariable String id) {
        log.info("DeleteActivity requested.");

        try {
            activityRepository.deleteById(UUID.fromString(id));
        } catch (Exception e) {
            e.printStackTrace();
            return MessageResponse.of(MessageType.ERROR, e.getMessage());
        }
        return MessageResponse.of(MessageType.SUCCESS, Constants.ACTIVITY_DELETED);
    }

    @PutMapping(path = "/register")
    public MessageResponse register(@RequestBody ActivityRegistrationRequestContent content) {
        log.info("RegisterActivity requested.");

        try {
            activityRegistrationService.registerUserToActivity(content);
        } catch (Exception e) {
            e.printStackTrace();
            return MessageResponse.of(MessageType.ERROR, e.getMessage());
        }
        return MessageResponse.of(MessageType.SUCCESS, Constants.REGISTERED);
    }

    @GetMapping(path = "/registeredUsers/{id}")
    public List<UserQueryResponse> getRegisteredUsers(@PathVariable String id) {
        log.info("getRegisteredUsers requested.");

        return activityService.getRegisteredUser(UUID.fromString(id))
                .stream()
                .map(UserQueryResponse::new)
                .toList();
    }

    @GetMapping(path = "/activityCountChartValue")
    public List<ActivityCountChartValueResponse> getActivityCountChartValue() {
        log.info("getRegisteredUsers requested.");

        return activityService.getAllActivities()
                .stream()
                .map(ActivityCountChartValueResponse::new)
                .toList();
    }

    @GetMapping(path = "/activityDateChartValue/{id}")
    public List<ActivityDateChartValueResponse> getActivityDateChartValue(@PathVariable String id) {
        log.info("getRegisteredUsers requested.");
        return chartService.getDateChartValue(UUID.fromString(id));
    }

    //Dış kullanıcı endpointleri
    @GetMapping(path = "/registeredActivities/{tcId}")
    public List<ActivityQueryResponse> getRegisteredActivities(@PathVariable long tcId) {
        log.info("getRegisteredActivities requested.");
        return activityRegistrationService.getRegisteredActivities(tcId)
                .stream()
                .map(ActivityQueryResponse::new)
                .toList();
    }

    @GetMapping(path = "/nonExpiredActivities")
    public List<ActivityQueryResponse> getNonExpiredActivities() {
        log.info("getNonExpiredActivities requested.");

        return activityService.getNonExpiredActivities()
                .stream()
                .map(ActivityQueryResponse::new)
                .toList();
    }


}

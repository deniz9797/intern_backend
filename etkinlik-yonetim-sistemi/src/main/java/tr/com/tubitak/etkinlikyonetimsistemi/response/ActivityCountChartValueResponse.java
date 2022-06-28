package tr.com.tubitak.etkinlikyonetimsistemi.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tr.com.tubitak.etkinlikyonetimsistemi.entity.Activity;
import tr.com.tubitak.etkinlikyonetimsistemi.entity.User;

import java.util.Map;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ActivityCountChartValueResponse {
    UUID activityId;

    Integer registrationCount;

    public ActivityCountChartValueResponse(Activity activity){
        activityId = activity.getActivityId();
        registrationCount = activity.getRegisteredCustomers().size();
    }
}

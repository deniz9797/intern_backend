package tr.com.tubitak.etkinlikyonetimsistemi.response;

import lombok.Getter;
import tr.com.tubitak.etkinlikyonetimsistemi.entity.Activity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;
@Getter
public class ActivityQueryResponse {
    private final UUID activityId;

    private final String activityName;

    private final LocalDateTime startDate;

    private final LocalDateTime endDate;

    private final Integer quota;

    public ActivityQueryResponse(Activity activity){
        this.activityId = activity.getActivityId();
        this.activityName = activity.getActivityName();
        this.startDate = activity.getStartDate();
        this.endDate = activity.getEndDate();
        this.quota = activity.getQuota();
    }
}

package tr.com.tubitak.etkinlikyonetimsistemi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tr.com.tubitak.etkinlikyonetimsistemi.content.ActivityUpdateRequestContent;
import tr.com.tubitak.etkinlikyonetimsistemi.util.ErrorType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "avtivity", schema = "public")
@Getter
public class Activity {
    @Id
    private UUID activityId;

    private String activityName;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private int quota;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @MapKey(name = "tcId")
    private Map<Long, User> registeredCustomers;

    public Activity(String activityName, LocalDateTime startDate, LocalDateTime endDate, int quota) {
        Preconditions.checkArgument(startDate.isBefore(endDate), ErrorType.INVALID_START_END_DATE);
        this.registeredCustomers = new HashMap<>();
        this.activityId = UUID.randomUUID();
        this.activityName = activityName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.quota = quota;
    }

    @Override
    public String toString() {
        return "Activity(activityId:" + activityId + ", activityName:" + activityName + ", startDate:" + startDate + ", endDate:" + endDate + ")";
    }

    public void update(ActivityUpdateRequestContent content) {
        Preconditions.checkArgument(startDate.isBefore(endDate), ErrorType.INVALID_START_END_DATE);
        Preconditions.checkArgument(!startDate.isBefore(LocalDateTime.now()), ErrorType.ACTIVITY_ALREADY_STARTED);

        this.activityName = content.getActivityName();
        this.startDate = content.getStartDate();
        this.endDate = content.getEndDate();
        this.quota = content.getQuota();
    }

    public void register(User user) {
        Preconditions.checkArgument(registeredCustomers.size() < this.quota, ErrorType.ACTIVITY_QUOTA_IS_FULL);
        Preconditions.checkArgument(!registeredCustomers.containsKey(user.getTcId()), ErrorType.CUSTOMER_ALREADY_REGISTERED);
        registeredCustomers.put(user.getTcId(), user);
    }
}

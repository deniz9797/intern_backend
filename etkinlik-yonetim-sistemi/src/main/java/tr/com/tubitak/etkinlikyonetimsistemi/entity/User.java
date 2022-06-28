package tr.com.tubitak.etkinlikyonetimsistemi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tr.com.tubitak.etkinlikyonetimsistemi.content.UserSignUpRequestContent;
import tr.com.tubitak.etkinlikyonetimsistemi.util.ErrorType;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "user", schema = "public")
public class User {
    @Id
    long tcId;

    String userName;

    String password;

    boolean isAdmin;

    @JsonIgnore
    @ManyToMany
    @MapKey(name = "activityId")
    Map<UUID, Activity> registeredActivities;

    public User(long tcId, String userName, String password, boolean isAdmin) {
        checkValidationTCID(tcId);
        this.tcId = tcId;
        this.userName = userName;
        this.password = password;
        this.isAdmin = isAdmin;
        this.registeredActivities = new HashMap<>();
    }
    public void registered(Activity activity) {
        registeredActivities.put(activity.getActivityId(), activity);
    }

    public static User of(UserSignUpRequestContent content, boolean isAdmin) {
        return new User(content.getCustomerTCId(),
                content.getUserName(),
                content.getPassword(), isAdmin);
    }



    private void checkValidationTCID(long id) {
        Preconditions.checkNotNull(id, ErrorType.INVALID_TCID);
        Preconditions.checkArgument(String.valueOf(id).length() == 11, ErrorType.INVALID_TCID);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

package tr.com.tubitak.etkinlikyonetimsistemi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tr.com.tubitak.etkinlikyonetimsistemi.entity.value.RegistrationKey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@IdClass(RegistrationKey.class)
public class Registration {
    @Id
    UUID activityId;

    @Id
    long tcId;

    @OneToOne
    Activity activity;

    @OneToOne
    User user;

    LocalDate registrationDate;


    public Registration(User user, Activity activity) {
        this.activityId = activity.getActivityId();
        this.tcId = user.getTcId();
        this.activity = activity;
        this.user = user;
        this.registrationDate = LocalDate.now();
    }


}

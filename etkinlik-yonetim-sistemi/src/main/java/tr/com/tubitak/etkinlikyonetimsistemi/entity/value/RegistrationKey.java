package tr.com.tubitak.etkinlikyonetimsistemi.entity.value;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

public class RegistrationKey implements Serializable {
    UUID activityId;
    long tcId;
}

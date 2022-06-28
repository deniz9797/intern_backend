package tr.com.tubitak.etkinlikyonetimsistemi.content;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActivityRegistrationRequestContent {
    @NotBlank
    long customerId;

    @NotBlank
    UUID activityId;
}

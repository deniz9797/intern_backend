package tr.com.tubitak.etkinlikyonetimsistemi.content;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActivityUpdateRequestContent {
    @NotBlank
    String activityName;

    @NotBlank
    LocalDateTime startDate;

    @NotBlank
    LocalDateTime endDate;

    @NotBlank
    @Min(value = 1)
    int quota;
}

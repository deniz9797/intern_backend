package tr.com.tubitak.etkinlikyonetimsistemi.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import tr.com.tubitak.etkinlikyonetimsistemi.entity.Activity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ActivityDateChartValueResponse {
    LocalDate registrationDate;

    Integer registrationCount;
}

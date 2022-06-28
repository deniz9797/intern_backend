package tr.com.tubitak.etkinlikyonetimsistemi.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.tubitak.etkinlikyonetimsistemi.entity.Registration;
import tr.com.tubitak.etkinlikyonetimsistemi.infrastructure.RegistrationRepository;
import tr.com.tubitak.etkinlikyonetimsistemi.response.ActivityDateChartValueResponse;

import java.time.LocalDate;
import java.util.*;

@Service
public class ChartService {
    @Autowired
    RegistrationRepository registrationRepository;

    public List<ActivityDateChartValueResponse> getDateChartValue(UUID activityId) {
        List<Registration> registrations = registrationRepository.findAllByActivityId(activityId);
        Map<LocalDate, Integer> dateCountMap = new HashMap<>();
        for (Registration reg : registrations) {
            LocalDate regDate = reg.getRegistrationDate();
            addOrIncrement(regDate, dateCountMap);
        }
        List<ActivityDateChartValueResponse> responseList = new ArrayList<>();

        dateCountMap.forEach((date, count) -> responseList.add(new ActivityDateChartValueResponse(date, count)));

        return responseList;
    }

    private void addOrIncrement(LocalDate date, Map<LocalDate, Integer> dateCountMap) {
        if (dateCountMap.containsKey(date)) {
            dateCountMap.put(date, dateCountMap.get(date) + 1);
        } else {
            dateCountMap.put(date, 1);
        }
    }
}

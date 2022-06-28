package tr.com.tubitak.etkinlikyonetimsistemi.response;

import lombok.Getter;
import tr.com.tubitak.etkinlikyonetimsistemi.entity.User;

@Getter
public class UserQueryResponse {

    long tcId;

    String userName;

    public UserQueryResponse(User user) {
        this.tcId = user.getTcId();
        this.userName = user.getUserName();
    }
}

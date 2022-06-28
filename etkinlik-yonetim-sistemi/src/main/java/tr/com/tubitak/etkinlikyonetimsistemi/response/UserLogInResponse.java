package tr.com.tubitak.etkinlikyonetimsistemi.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tr.com.tubitak.etkinlikyonetimsistemi.entity.Activity;
import tr.com.tubitak.etkinlikyonetimsistemi.entity.User;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserLogInResponse {

    Boolean isSuccess;

    User user;

    public UserLogInResponse(Optional<User> optUser){
        optUser.ifPresentOrElse(user ->{
            this.isSuccess = true;
            this.user = user;
        }, () -> {
            this.isSuccess = false;
            this.user = null;
        });
    }

}

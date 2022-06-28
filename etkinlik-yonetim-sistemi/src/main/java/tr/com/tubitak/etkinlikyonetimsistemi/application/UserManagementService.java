package tr.com.tubitak.etkinlikyonetimsistemi.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.tubitak.etkinlikyonetimsistemi.content.UserLogInRequestContent;
import tr.com.tubitak.etkinlikyonetimsistemi.content.UserSignUpRequestContent;
import tr.com.tubitak.etkinlikyonetimsistemi.entity.User;
import tr.com.tubitak.etkinlikyonetimsistemi.infrastructure.UserRepository;
import tr.com.tubitak.etkinlikyonetimsistemi.response.UserLogInResponse;
import tr.com.tubitak.etkinlikyonetimsistemi.util.ErrorType;

@Service
@RequiredArgsConstructor
public class UserManagementService {

    private final UserRepository userRepository;

    public void signUpUser(UserSignUpRequestContent content) {
        userRepository.findById(content.getCustomerTCId()).ifPresent(activity -> {
            throw new IllegalArgumentException(ErrorType.USER_ALREADY_EXIST);
        });

        User user = User.of(content, false);

        userRepository.save(user);
    }

    public UserLogInResponse logInUser(UserLogInRequestContent content) {
        return new UserLogInResponse(userRepository.findByUserNameAndPassword(content.getUserName(), content.getPassword()));
    }


}

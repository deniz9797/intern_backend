package tr.com.tubitak.etkinlikyonetimsistemi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tr.com.tubitak.etkinlikyonetimsistemi.application.UserManagementService;
import tr.com.tubitak.etkinlikyonetimsistemi.content.UserLogInRequestContent;
import tr.com.tubitak.etkinlikyonetimsistemi.content.UserSignUpRequestContent;
import tr.com.tubitak.etkinlikyonetimsistemi.response.MessageResponse;
import tr.com.tubitak.etkinlikyonetimsistemi.response.MessageType;
import tr.com.tubitak.etkinlikyonetimsistemi.response.UserLogInResponse;
import tr.com.tubitak.etkinlikyonetimsistemi.util.Constants;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class UserController {

    private final UserManagementService userManagementService;

    @PostMapping(path = "/signUp")
    public MessageResponse signUp(@RequestBody UserSignUpRequestContent content) {
        log.info("SignUpUser requested.");

        try {
            userManagementService.signUpUser(content);
        } catch (Exception e) {
            return MessageResponse.of(MessageType.ERROR, e.getMessage());
        }

        return MessageResponse.of(MessageType.SUCCESS, Constants.USER_REGISTERED);
    }

    @PostMapping(path = "/login")
    public UserLogInResponse login(@RequestBody UserLogInRequestContent content) {
        log.info("login requested.");
        return userManagementService.logInUser(content);
    }

}

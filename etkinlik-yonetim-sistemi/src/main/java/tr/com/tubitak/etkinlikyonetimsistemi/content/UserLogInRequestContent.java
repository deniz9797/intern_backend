package tr.com.tubitak.etkinlikyonetimsistemi.content;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLogInRequestContent {
    String userName;

    String password;

}

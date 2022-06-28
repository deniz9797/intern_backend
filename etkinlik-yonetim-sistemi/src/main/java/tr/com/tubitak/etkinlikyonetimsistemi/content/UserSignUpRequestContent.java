package tr.com.tubitak.etkinlikyonetimsistemi.content;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSignUpRequestContent {
    @NotBlank
    long customerTCId;
    @NotBlank
    String userName;
    @NotBlank
    String password;

}

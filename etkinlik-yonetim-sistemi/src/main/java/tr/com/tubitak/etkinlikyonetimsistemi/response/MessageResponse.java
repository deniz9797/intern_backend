package tr.com.tubitak.etkinlikyonetimsistemi.response;

import lombok.Getter;

@Getter
public class MessageResponse {
    private final MessageType messageType;
    private final String message;

    private MessageResponse(MessageType messageType,
                            String message) {
        this.messageType = messageType;
        this.message = message;
    }

    public static MessageResponse of(MessageType messageType, String message) {
        return new MessageResponse(messageType, message);
    }

}

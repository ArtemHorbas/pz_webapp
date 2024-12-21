package kad.production.pz_webapp.message;

import lombok.Getter;

@Getter
public enum LoginMessage {
    FIRST("MESSAGE FIRST LOGIN"),
    SUBSEQUENT("MESSAGE SUBSEQUENT LOGIN");

    private final String text;

    LoginMessage(String text) {
        this.text = text;
    }

}

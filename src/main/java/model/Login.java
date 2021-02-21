package model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Login {

    String mail;
    String password;
}

package model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Register {

    String firstName;
    String lastName;
    String mail;
    String password;
    String phone;
}

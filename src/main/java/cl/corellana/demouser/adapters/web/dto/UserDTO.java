package cl.corellana.demouser.adapters.web.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserDTO {
    private String name;

    @NotNull
    @Email(message = "Invalid email")
    private String email;

    private PhoneDTO[] phones;

    @NotNull
    @Pattern(regexp = "^(?=(?:[^A-Z]*[A-Z]){1}[^A-Z]*$)(?=(?:\\D*\\d){2}\\D*$)[a-zA-Z\\d]{8,12}$", message = "Password must have a length between 8 and 12 characters, only one capital letter and only two numbers")
    private String password;

}

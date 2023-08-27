package tn.esprit.welcometoesprit_hexapod_4se1.entities;

import lombok.*;

import javax.validation.Valid;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Valid
public class LoginRequest {
    private String mail;
    private String password;
}

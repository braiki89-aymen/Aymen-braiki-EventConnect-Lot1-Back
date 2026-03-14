package pidev.eventconnect.login.Dto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignupRequest {
    String nom;
    String prenom;
    String password;
    String email;




}

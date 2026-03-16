package tn.esprit.tic.se.spr01.UserManagement.DTO;


import lombok.Data;
import tn.esprit.tic.se.spr01.UserManagement.Entities.User;
import tn.esprit.tic.se.spr01.UserManagement.Entities.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;
/**
 * DTO for {@link User}
 */

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto{
        private String firstname;
        private String password;
        private String email;
        private Status status;
        private String avatarUrl;

        private Long Id;
        private String lastName;
        private String position;
        private Long clubId;
        private Long teamId;





    public static UserDto toDto(User entity) {
        if (entity == null) {
            return null;

        }

        return tn.esprit.tic.se.spr01.UserManagement.DTO.UserDto.builder()
                .firstname(entity.getFirstname())
                .lastName(entity.getLastname())
                .Id(entity.getId())
                .build();

    }


}


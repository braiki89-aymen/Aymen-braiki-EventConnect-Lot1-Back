package tn.esprit.tic.se.spr01.UserManagement.Mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tn.esprit.tic.se.spr01.UserManagement.DTO.UserDto;
import tn.esprit.tic.se.spr01.UserManagement.Entities.User;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserDto toDTO(final User user, final UserDto userDTO) {
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setStatus(user.getStatus());
        userDTO.setAvatarUrl(user.getAvatarUrl());
        return userDTO;
    }


    public User toEntity(final UserDto userDTO, final User user) {
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setStatus(userDTO.getStatus());
        return user;
    }

}

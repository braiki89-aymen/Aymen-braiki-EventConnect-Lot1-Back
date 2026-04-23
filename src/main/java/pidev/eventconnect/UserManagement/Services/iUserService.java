package tn.esprit.tic.se.spr01.UserManagement.Services;


import org.springframework.web.multipart.MultipartFile;
import tn.esprit.tic.se.spr01.UserManagement.DTO.UserDto;
import tn.esprit.tic.se.spr01.UserManagement.Entities.Status;
import tn.esprit.tic.se.spr01.UserManagement.Entities.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface iUserService {

    User addUser(User user);
    User getUserById(Long id);
    Optional<User> getUserByEmail(String email);

    User updateUser(Long id, User user);
    void deleteUser(Long id);

    UserDto login(final UserDto userDTO);
    void validatePassword(User user, String password);
    User createUser(String firstname, String password);
    UserDto connect(UserDto userDTO);
    UserDto logout(String firstname);
    List<UserDto> getAllOnlineUsers();
    List<UserDto> searchUsers(String firstname);
    Status getUserStatus(String firstname);
//    List<UserDto> searchUsersWithExclusion(String firstname, List<UserDto> excludedUsers);
    UserDto updateAvatar(String firstname, MultipartFile avatar);
    List<UserDto> searchUsersWithExclusion(String firstname, List<Long> excludedIds);
}

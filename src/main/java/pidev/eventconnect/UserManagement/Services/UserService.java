package tn.esprit.tic.se.spr01.UserManagement.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.tic.se.spr01.UserManagement.DTO.UserDto;
import tn.esprit.tic.se.spr01.UserManagement.Entities.User;
import tn.esprit.tic.se.spr01.UserManagement.Entities.Status;
import tn.esprit.tic.se.spr01.UserManagement.Mapper.UserMapper;
import tn.esprit.tic.se.spr01.UserManagement.Repositories.UserRepository;
import tn.esprit.tic.se.spr01.utils.FileUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements iUserService{


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final FileUtil fileUtil;

    // Create
    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    // Read
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    // Update
    @Override
    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        existingUser.setFirstname(user.getFirstname());
        existingUser.setLastname(user.getLastname());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setDateOfBirth(user.getDateOfBirth());
        existingUser.setEnabled(user.isEnabled());
        existingUser.setAccountLocked(user.isAccountLocked());
        return userRepository.save(existingUser);
    }

    // Delete
    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }
    
    // Rechercher un utilisateur par email
    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public UserDto login(final UserDto userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail())
                .orElseGet(() -> createUser(userDTO.getEmail(), userDTO.getPassword()));

        validatePassword(user, userDTO.getPassword());

        return userMapper.toDTO(user, new UserDto());
    }


    public void validatePassword(User user, String password) {
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password");
        }
    }


    public User createUser(String email, String password) {
        User user = User.builder()
                .email(email)
                .password(password)
                .status(Status.ONLINE)
                .lastLogin(LocalDateTime.now())
                .build();
        return userRepository.save(user);
    }


    public UserDto connect(UserDto userDTO) {
        Optional<User> user = userRepository.findByEmail(userDTO.getEmail());
        user.ifPresent(u -> {
            u.setStatus(Status.ONLINE);
            userRepository.save(u);
        });
        return user.map(u -> userMapper.toDTO(u, new UserDto())).orElse(null);
    }


    public UserDto logout(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        user.ifPresent(u -> {
            u.setStatus(Status.OFFLINE);
            u.setLastLogin(LocalDateTime.now());
            userRepository.save(u);
        });
        return user.map(u -> userMapper.toDTO(u, new UserDto())).orElse(null);
    }


    public List<UserDto> getAllOnlineUsers() {
        return userRepository.findAllByStatus(Status.ONLINE)
                .stream()
                .map(u -> userMapper.toDTO(u, new UserDto()))
                .toList();
    }


    public List<UserDto> searchUsers(String firstname) {
        return userRepository.findTop20ByFirstnameContaining(firstname)
                .stream()
                .map(u -> userMapper.toDTO(u, new UserDto()))
                .toList();
    }



    public List<UserDto> searchUsersWithExclusion(String firstname, List<Long> excludedIds) {
            List<User> users;

            if (excludedIds == null || excludedIds.isEmpty()) {
                // fallback sur la recherche simple
                users = userRepository.findTop20ByFirstnameContainingIgnoreCase(firstname);
            } else {
                // vraie recherche avec exclusion
                users = userRepository.findTop20ByFirstnameContainingIgnoreCaseAndIdNotIn(
                        firstname,
                        excludedIds
                );
            }

            return users.stream()
                    .map(u -> userMapper.toDTO(u, new UserDto()))
                    .toList();
        }




    public Status getUserStatus(String email) {
        return userRepository.findByEmail(email)
                .map(User::getStatus)
                .orElse(Status.OFFLINE);
    }


//    public List<UserDto> searchUsersWithExclusion(String firstname, List<UserDto> excludedUsers) {
//        List<String> firstnames = excludedUsers.stream()
//                .map(UserDto::getFirstname)
//                .toList();
//        return userRepository.findTop20ByFirstnameContainingAndFirstnameNotIn(firstname, firstnames)
//                .stream()
//                .map(u -> userMapper.toDTO(u, new UserDto()))
//                .toList();
//    }


    public UserDto updateAvatar(String email, MultipartFile avatar) {
        // upload avatar to storage
        final Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            if(user.get().getAvatarUrl() != null) {
                try {
                    fileUtil.deleteFile(user.get().getAvatarShortUrl());
                } catch (Exception e) {
                    throw new IllegalArgumentException("Failed to delete old avatar");
                }
            }
            String avatarUrl = fileUtil.storeFile(avatar, "avatars");
            user.get().setAvatarUrl(avatarUrl);
            userRepository.save(user.get());
        }
        return user.map(u -> userMapper.toDTO(u, new UserDto())).orElse(null);
    }


}

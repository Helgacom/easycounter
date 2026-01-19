package easycounter.service;

import easycounter.dto.UserDTO;
import easycounter.dto.request.SignupRequestDTO;
import easycounter.dto.response.RegisterDTO;
import easycounter.mapper.UserMapper;
import easycounter.model.ERole;
import easycounter.model.Role;
import easycounter.model.User;
import easycounter.repository.RoleRepository;
import easycounter.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private PasswordEncoder encoder;

    private final RoleRepository roleRepository;

    public User create(UserDTO user) {
        var newUser = userMapper.userDtoToUser(user);
        userRepository.save(newUser);
        return newUser;
    }

    public boolean update(UserDTO user) {
        var newUser = userMapper.userDtoToUser(user);
        return userRepository.update(newUser) > 0L;
    }

    public boolean deleteById(Long userId) {
        return userRepository.delete(userId) > 0L;
    }

    public RegisterDTO signUp(SignupRequestDTO signUpRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByName(signUpRequest.getUsername()))
                || Boolean.TRUE.equals(userRepository.existsByLogin(signUpRequest.getLogin()))) {
            return new RegisterDTO(HttpStatus.BAD_REQUEST, "Error: Username or Email is already taken!" );
        }

        User user = new User(signUpRequest.getUsername(), signUpRequest.getLogin(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        Supplier<RuntimeException> supplier = () -> new RuntimeException("Error: Role is not found.");

        if (strRoles == null) {
            roles.add(roleRepository.findByName(ERole.ROLE_USER).orElseThrow(supplier));
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ADMIN" -> roles.add(roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(supplier));
                    case "MODERATOR" -> roles.add(roleRepository.findByName(ERole.ROLE_MODERATOR).orElseThrow(supplier));
                    default -> roles.add(roleRepository.findByName(ERole.ROLE_USER).orElseThrow(supplier));
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return new RegisterDTO(HttpStatus.OK, "User registered successfully!" );
    }
}

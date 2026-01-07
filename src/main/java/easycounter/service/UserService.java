package easycounter.service;

import easycounter.dto.UserDTO;
import easycounter.mapper.UserMapper;
import easycounter.model.User;
import easycounter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

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
}

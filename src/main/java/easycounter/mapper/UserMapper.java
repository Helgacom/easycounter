package easycounter.mapper;

import easycounter.dto.UserDTO;
import easycounter.model.User;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link User} and its DTO {@link UserDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    User userDtoToUser(UserDTO userDto);
}

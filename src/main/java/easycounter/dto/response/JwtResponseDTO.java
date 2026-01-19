package easycounter.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponseDTO {
    private Long id;
    private String username;
    private String login;
    private List<String> roles;

    public JwtResponseDTO(Long id, String username, String login, List<String> roles) {
        this.id = id;
        this.username = username;
        this.login = login;
        this.roles = roles;
    }
}

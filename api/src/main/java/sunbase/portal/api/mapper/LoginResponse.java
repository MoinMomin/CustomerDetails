package sunbase.portal.api.mapper;
import sunbase.portal.api.model.User;
import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private User user;
}

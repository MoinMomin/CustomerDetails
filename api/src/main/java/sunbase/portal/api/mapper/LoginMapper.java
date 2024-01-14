package sunbase.portal.api.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginMapper {
    @JsonProperty(value = "login_id")
    private String userName;
    private String password;
}

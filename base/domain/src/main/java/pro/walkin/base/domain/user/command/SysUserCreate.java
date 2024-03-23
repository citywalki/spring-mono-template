package pro.walkin.base.domain.user.command;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import pro.walkin.framework.api.Command;

@Data
@Builder
public class SysUserCreate implements Command<String> {

    @JsonAlias("name")
    @NotEmpty
    private String userId;

    private String comment;

    @NotEmpty
    private String realName;

    @NotEmpty
    @Email
    private String email;

}

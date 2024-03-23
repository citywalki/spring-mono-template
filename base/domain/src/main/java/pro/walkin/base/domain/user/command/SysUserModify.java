package pro.walkin.base.domain.user.command;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import pro.walkin.framework.api.Command;

@Data
public class SysUserModify implements Command<Void> {

    @NotBlank
    @JsonAlias("objectKey")
    private String userKey;

    @NotBlank
    @JsonAlias("objectId")
    private String userId;

    private String realName;

    private String email;

    private String comment;

}

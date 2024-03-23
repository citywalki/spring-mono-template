package pro.walkin.base.domain.user.command;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.walkin.framework.api.Command;
import pro.walkin.framework.biz.Languages;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUserCheckLogin implements Command<Void> {
    @NotBlank(message = "username null")
    private String username;

    private String phone;

    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String facility;

    private Languages language;

}

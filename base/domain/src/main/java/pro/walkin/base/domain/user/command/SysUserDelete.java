package pro.walkin.base.domain.user.command;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.walkin.framework.api.Command;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUserDelete implements Command<Void> {

    @JsonAlias("objectKey")
    private String userKey;

}

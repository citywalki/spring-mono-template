package pro.walkin.base.domain.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pro.walkin.framework.dict.annotation.Dict;
import pro.walkin.framework.entity.BaseEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@TableName("USER_PROFILE")
@Schema(name = "用户信息")
public class SysUser extends BaseEntity<SysUser> {

    private String userName;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "真实姓名")
    private String realName;

    @Schema(description = "邮箱")
    private String email;

    private String phone;

    @Schema(description = "头像")
    private String avatar;

    @Dict(dictCode = "sys_user_status")
    @Schema(description = "状态")
    private String status;


    public boolean isUserExist() {
        return selectCount(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUserName, getUserName())) != 0;
    }

}
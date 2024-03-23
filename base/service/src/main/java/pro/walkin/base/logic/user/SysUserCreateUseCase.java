package pro.walkin.base.logic.user;

import lombok.RequiredArgsConstructor;
import org.dromara.hutool.core.lang.Assert;
import org.dromara.hutool.crypto.digest.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.walkin.base.domain.BaseConstant;
import pro.walkin.base.domain.parameter.SysParameter;
import pro.walkin.base.domain.user.SysUser;
import pro.walkin.base.domain.user.command.SysUserCreate;
import pro.walkin.framework.api.UseCase;

@Service
@Transactional
@RequiredArgsConstructor
public class SysUserCreateUseCase implements UseCase<SysUserCreate, String> {


    @Override
    public String handle(SysUserCreate userCreate) {

        var sysUser = SysUser
                .builder()
                .userName(userCreate.getUserId())
                .email(userCreate.getEmail())
                .realName(userCreate.getRealName())
                .password(getPassword())
                .build();

        Assert.isFalse(sysUser.isUserExist());

        sysUser.insert();
        return sysUser.getObjectKey();
    }

    /**
     * 如果配置的密码参数不是RANDOM，就把值当密码
     *
     * @return 密码
     */
    private String getPassword() {
        var password = new SysParameter()
                .getForPwdGeneratorPolicy()
                .filter(parameter -> !BaseConstant.PWD_GENERATOR_POLICY_RANDOM.equals(parameter))
                .orElseGet(() -> String.valueOf((int) ((Math.random() * 9 + 1) * 100000)));

        return BCrypt.hashpw(password);
    }

}

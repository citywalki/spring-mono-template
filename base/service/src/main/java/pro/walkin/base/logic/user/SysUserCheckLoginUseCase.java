package pro.walkin.base.logic.user;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.dromara.hutool.core.lang.Assert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.walkin.base.domain.user.SysUser;
import pro.walkin.base.domain.user.command.SysUserCheckLogin;
import pro.walkin.base.message.SysUserMessages;
import pro.walkin.framework.api.UseCase;
import pro.walkin.framework.tracer.BizBaggageContext;

@Service
@Transactional
public class SysUserCheckLoginUseCase implements UseCase<SysUserCheckLogin, Void> {

    @Override
    public Void handle(SysUserCheckLogin command) {

        var wrapper = new LambdaQueryWrapper<SysUser>()
                .or(wrp -> wrp.eq(SysUser::getEmail, command.getEmail()))
                .or(wrp -> wrp.eq(SysUser::getPhone, command.getPhone()))
                .or(wrp -> wrp.eq(SysUser::getUserName, command.getUsername()));

        var currentUser = new SysUser().selectOne(wrapper);
        Assert.isNull(currentUser, () -> SysUserMessages.USER_MESSAGES.usernameNotFound(command.getUsername()));

        if (BCrypt.checkpw(command.getPassword(), currentUser.getPassword())) {
            // 登录上
            StpUtil.login(currentUser.getObjectKey(), SaLoginConfig
                    .setExtra(BizBaggageContext.KEY_FACILITY, command.getFacility())
                    .setExtra(BizBaggageContext.KEY_LANGUAGE, command.getLanguage()));
        } else {
            throw SysUserMessages.USER_MESSAGES.passwordInvalid();
        }

        return null;
    }

}

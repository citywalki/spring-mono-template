package pro.walkin.base.logic.user;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.StpUtil;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.walkin.base.domain.user.QSysUser;
import pro.walkin.base.domain.user.command.SysUserCheckLogin;
import pro.walkin.base.logic.user.repository.SysUserRepository;
import pro.walkin.base.message.SysUserMessages;
import pro.walkin.framework.api.UseCase;
import pro.walkin.framework.tracer.BizBaggageContext;

@Service
@Transactional
public class SysUserCheckLoginUseCase implements UseCase<SysUserCheckLogin, Void> {

    private SysUserRepository sysUserRepository;

    @Override
    public Void handle(SysUserCheckLogin command) {

        var currentUser = sysUserRepository
                .findOne(Expressions.anyOf(QSysUser.sysUser.email.eq(command.getEmail()),
                                           QSysUser.sysUser.phone.eq(command.getPhone()),
                                           QSysUser.sysUser.userName.eq(command.getUsername())))
                .orElseThrow(() -> SysUserMessages.USER_MESSAGES.usernameNotFound(command.getUsername()));

        if (BCrypt.checkpw(command.getPassword(), currentUser.getPassword())) {
            // 登录上
            StpUtil.login(currentUser.getId(), SaLoginConfig
                    .setExtra(BizBaggageContext.KEY_FACILITY, command.getFacility())
                    .setExtra(BizBaggageContext.KEY_LANGUAGE, command.getLanguage()));
        } else {
            throw SysUserMessages.USER_MESSAGES.passwordInvalid();
        }

        return null;
    }

    @Autowired
    public void setSysUserRepository(SysUserRepository sysUserRepository) {
        this.sysUserRepository = sysUserRepository;
    }

}

package pro.walkin.base.message;

import org.jboss.logging.Messages;
import org.jboss.logging.annotations.Message;
import org.jboss.logging.annotations.MessageBundle;
import pro.walkin.base.domain.BaseConstant;
import pro.walkin.framework.exception.BizException;
import pro.walkin.framework.exception.CommonMessage;
import pro.walkin.framework.exception.UsernameNotFoundException;

@MessageBundle(projectCode = BaseConstant.BASIC_MESSAGE_CODE)
public interface SysUserMessages extends CommonMessage {
    SysUserMessages USER_MESSAGES = Messages.getBundle(SysUserMessages.class);

    @Message(id = 1, value = "该用户%s不存在")
    UsernameNotFoundException usernameNotFound(String username);

    @Message(id = 2, value = "该用户实例%s不存在")
    UsernameNotFoundException userIdNotFound(String userId);

    @Message("密码校验不通过")
    BizException passwordInvalid();

    @Message("用户%s已经存在")
    RuntimeException usernameIsExits(String userName);

}

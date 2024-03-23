package pro.walkin.base.message;

import org.jboss.logging.Messages;
import org.jboss.logging.annotations.Message;
import org.jboss.logging.annotations.MessageBundle;
import org.jboss.logging.annotations.ValidIdRange;
import pro.walkin.base.domain.BaseConstant;
import pro.walkin.framework.exception.BizException;
import pro.walkin.framework.exception.CommonMessage;

@MessageBundle(projectCode = BaseConstant.BASIC_MESSAGE_CODE)
@ValidIdRange(min = 1000, max = 1999)
public interface SysMenuMessages extends CommonMessage {
    SysMenuMessages MENU_MESSAGES = Messages.getBundle(SysMenuMessages.class);


    @Message(id = 1000, value = "该菜单实例%s不存在")
    BizException notFoundById(String id);

    @Message(id = 1001, value = "该路径%s不存在菜单")
    BizException notFoundByPath(String path);

    @Message(id = 1002, value = "找不到叫%s的菜单")
    BizException notFoundByName(String name);

    @Message(id = 1003, value = "菜单%s 没有绑定页面")
    BizException noBindScreen(String name);

    @Message(id = 1004, value = "菜单重名%s")
    BizException existsByObjectId(String menuId);

}

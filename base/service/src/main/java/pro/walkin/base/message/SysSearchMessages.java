package pro.walkin.base.message;

import org.jboss.logging.Messages;
import org.jboss.logging.annotations.Message;
import org.jboss.logging.annotations.MessageBundle;
import org.jboss.logging.annotations.ValidIdRange;
import pro.walkin.base.domain.BaseConstant;
import pro.walkin.framework.exception.BizException;
import pro.walkin.framework.exception.CommonMessage;

@MessageBundle(projectCode = BaseConstant.BASIC_MESSAGE_CODE)
@ValidIdRange(min = 2000, max = 2999)
public interface SysSearchMessages extends CommonMessage {

    SysSearchMessages SEARCH_MESSAGES = Messages.getBundle(SysSearchMessages.class);

    @Message(id = 2001, value = "该查询配置%s不存在")
    BizException searchNameNotFound(String searchName);

}

package pro.walkin.base.message;

import org.jboss.logging.Messages;
import org.jboss.logging.annotations.Message;
import org.jboss.logging.annotations.MessageBundle;
import org.jboss.logging.annotations.ValidIdRange;
import pro.walkin.base.domain.BaseConstant;
import pro.walkin.framework.exception.BizException;
import pro.walkin.framework.exception.CommonMessage;

@MessageBundle(projectCode = BaseConstant.BASIC_MESSAGE_CODE)
@ValidIdRange(min = 3000, max = 3999)
public interface SysScreenMessages extends CommonMessage {
    SysScreenMessages SCREEN_MESSAGES = Messages.getBundle(SysScreenMessages.class);

    @Message(id = 3000, value = "ScreenView ID:%s is not exit")
    BizException screenViewNoDefinition(String screenViewRrn);

    @Message(id = 3001, value = "ScreenView ID:%s Version:%s not definition schema")
    BizException schemaNotDefinition(String screenView, long schema);

    @Message(id = 3002, value = "Screen Name:%s already existed")
    BizException screenNameAlreadyExisted(String name);

    @Message(id = 3003, value = "Screen Schema ID:%s is not exist")
    BizException screenSchemaNotDefinition(String screenSchemaRrn);

    @Message(id = 3004, value = "Deletion is only allowed in the unfreeze state")
    BizException onlyUnFreezeAllowDelete();

}

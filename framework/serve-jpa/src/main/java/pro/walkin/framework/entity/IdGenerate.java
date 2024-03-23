package pro.walkin.framework.entity;

import org.dromara.hutool.core.data.id.ObjectId;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class IdGenerate implements IdentifierGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        return ObjectId.next();
    }

}

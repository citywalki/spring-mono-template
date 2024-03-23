package pro.walkin.framework.exception;

import org.jboss.logging.annotations.MessageBundle;
import pro.walkin.framework.tracer.BizBaggageHolder;

@MessageBundle(projectCode = "COM", length = 5)
public interface CommonMessage {
    default String language() {
        return BizBaggageHolder.getContext().language();
    }

}

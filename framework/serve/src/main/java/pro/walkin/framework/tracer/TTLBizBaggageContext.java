package pro.walkin.framework.tracer;

import com.alibaba.ttl.TransmittableThreadLocal;
import org.dromara.hutool.core.data.id.ObjectId;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TTLBizBaggageContext implements BizBaggageContext {

    private final ThreadLocal<Map<String, String>> localMap;

    public TTLBizBaggageContext() {
        localMap = new TransmittableThreadLocal<>();
    }

    @Override
    public void initRequest() {
        initRequest(ObjectId.next());
    }

    @Override
    public void initRequest(String traceId) {
        put(KEY_TRACE_ID, traceId);
    }

    @Override
    public String getValueByKey(String key) {
        return localMap.get().get(key);
    }

    @Override
    public void put(String key, String value) {
        Map<String, String> map = localMap.get();
        map = map == null ? new HashMap<>() : new HashMap<>(map);
        map.put(key, value);
        localMap.set(Collections.unmodifiableMap(map));

    }

    @Override
    public String traceId() {
        return Optional
                .of(getValueByKey(KEY_TRACE_ID))
                .orElseThrow(() -> new IllegalStateException("服务不能没有traceId"));
    }

    @Override
    public Integer spanId() {
        return Optional.of(getValueByKey(KEY_SPAN_ID)).map(Integer::valueOf).orElse(0);
    }


    @Override
    public void remove() {
        localMap.remove();
    }

}

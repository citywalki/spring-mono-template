package pro.walkin.framework.tracer;

public interface BizBaggageContext {

    String KEY_TRACE_ID = "traceId";
    String KEY_SPAN_ID = "spanId";
    String KEY_USER_RRN = "userRrn";
    String KEY_FACILITY = "facility";
    String KEY_LANGUAGE = "language";
    String KEY_USER_KEY = "userKey";
    String KEY_HOST = "host";
    String KEY_COMMAND = "command";
    String KEY_USE_CASE = "usecase";


    default String language() {
        return getValueByKey(KEY_LANGUAGE);
    }

    void initRequest();

    void initRequest(String traceId);

    String getValueByKey(String key);

    void put(String key, String value);

    String traceId();

    Integer spanId();

    void remove();

    default void language(String language) {
        put(KEY_LANGUAGE, language);
    }

    default String userKey() {
        return getValueByKey(KEY_USER_KEY);
    }

    default void userKey(String userKey) {
        put(KEY_USER_KEY, userKey);
    }

    default String facility() {
        return getValueByKey(KEY_FACILITY);
    }

    default void facility(String facility) {
        put(KEY_FACILITY, facility);
    }

}

package pro.walkin.framework.tracer;


public class BizBaggageHolder {
    private static final BizBaggageContext BIZ_BAGGAGE_CONTEXT = new TTLBizBaggageContext();

    public static BizBaggageContext getContext() {
        return BIZ_BAGGAGE_CONTEXT;
    }

}

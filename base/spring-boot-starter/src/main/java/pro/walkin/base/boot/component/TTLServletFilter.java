package pro.walkin.base.boot.component;

import cn.dev33.satoken.stp.StpUtil;
import jakarta.servlet.*;
import pro.walkin.framework.biz.Languages;
import pro.walkin.framework.tracer.BizBaggageContext;
import pro.walkin.framework.tracer.BizBaggageHolder;

import java.io.IOException;

public class TTLServletFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        try {
            BizBaggageHolder.getContext().initRequest();
            BizBaggageHolder.getContext().language(Languages.CN.toString());

            if (StpUtil.isLogin()) {
                BizBaggageHolder.getContext().userKey(StpUtil.getLoginIdAsString());

                if (StpUtil.getExtra(BizBaggageContext.KEY_LANGUAGE) != null) {
                    BizBaggageHolder
                            .getContext()
                            .language(String.valueOf(StpUtil.getExtra(BizBaggageContext.KEY_LANGUAGE)));
                }
            }

            chain.doFilter(request, response);
        } finally {
            BizBaggageHolder.getContext().remove();
        }
    }

}

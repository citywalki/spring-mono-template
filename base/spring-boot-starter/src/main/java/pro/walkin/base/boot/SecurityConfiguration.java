package pro.walkin.base.boot;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.jwt.StpLogicJwtForStateless;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;

public class SecurityConfiguration {

    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForStateless();
    }

    /**
     * 注册 [Sa-Token全局过滤器]
     */
    // @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()

                // 指定 拦截路由 与 放行路由
                .addInclude("/**")
                .addExclude("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**")
                .addExclude("/api/system/menu/getRoutes")
                .addExclude("/api/general/login/login")
                // 认证函数: 每次请求执行
                .setAuth(obj -> {

                    // 登录认证 -- 拦截所有路由
                    // StpUtil.checkLogin();

                    if (!StpUtil.isLogin()) {
                        // throw NotLoginException
                        //         .newInstance(this.loginType, "-2", "token 无效", tokenValue).setCode(11012);
                        SaHolder.getResponse().setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                        SaRouter.back();
                    }

                })
                // 异常处理函数：每次认证函数发生异常时执行此函数
                .setError(e -> SaResult.error(e.getMessage()))

                // 前置函数：在每次认证函数之前执行（BeforeAuth 不受 includeList 与 excludeList 的限制，所有请求都会进入）
                .setBeforeAuth(r -> {
                    // ---------- 设置一些安全响应头 ----------
                    SaHolder.getResponse()
                            // 服务器名称
                            .setServer("sa-server")
                            // 是否可以在iframe显示视图： DENY=不可以 | SAMEORIGIN=同域下可以 | ALLOW-FROM uri=指定域名下可以
                            .setHeader("X-Frame-Options", "SAMEORIGIN")
                            // 是否启用浏览器默认XSS防护： 0=禁用 | 1=启用 | 1; mode=block 启用, 并在检查到XSS攻击时，停止渲染页面
                            .setHeader("X-XSS-Protection", "1; mode=block")
                            // 禁用浏览器内容嗅探
                            .setHeader("X-Content-Type-Options", "nosniff");
                });
    }

}

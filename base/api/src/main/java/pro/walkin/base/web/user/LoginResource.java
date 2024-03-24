package pro.walkin.base.web.user;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.walkin.base.domain.user.command.SysUserCheckLogin;
import pro.walkin.base.web.BaseFeatureRest;
import pro.walkin.framework.tracer.BizBaggageHolder;
import pro.walkin.framework.web.Result;

@BaseFeatureRest
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginResource {

    @PostMapping("/login")
    @Operation(summary = "登录", description = "登录")
    public Result<?> doLogin(@RequestBody SysUserCheckLogin checkLogin) {
        BizBaggageHolder.getContext().facility(checkLogin.getFacility());
        checkLogin.fire();
        return Result.success(StpUtil.getTokenInfo());
    }

    @PostMapping("/logout")
    @Operation(summary = "注销", description = "注销")
    public Result<?> logout() {
        StpUtil.logout();
        return Result.success();
    }

}

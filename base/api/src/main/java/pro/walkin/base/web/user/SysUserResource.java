package pro.walkin.base.web.user;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pro.walkin.base.domain.user.SysUser;
import pro.walkin.base.domain.user.command.SysUserCreate;
import pro.walkin.base.domain.user.command.SysUserDelete;
import pro.walkin.base.domain.user.command.SysUserModify;
import pro.walkin.base.logic.user.query.SysUserQueries;
import pro.walkin.base.web.BaseFeatureRest;
import pro.walkin.base.web.SuperResource;
import pro.walkin.framework.web.Result;

@BaseFeatureRest
@RequestMapping("/user")
@RequiredArgsConstructor
public class SysUserResource extends SuperResource<SysUserQueries> {

    @PostMapping
    @Operation(summary = "创建", description = "创建用户")
    public Result<String> create(@RequestBody SysUserCreate command) {
        return Result.success(command.fire());
    }

    @DeleteMapping
    @Operation(summary = "删除", description = "删除用户")
    public Result<Void> delete(@RequestBody SysUserDelete command) {
        command.fire();
        return Result.success();
    }

    @PatchMapping
    @Operation(summary = "修改", description = "修改用户")
    public Result<Void> modify(SysUserModify command) {
        command.fire();
        return Result.success();
    }


    @GetMapping(value = "/getUserInfo")
    @Operation(summary = "获取当前用户信息", description = "获取当前用户信息")
    public Result<SysUser> getUserInfo() {
        // String userId = StpUtil.getLoginId().toString();

        return Result.success(query.findByIdCheckNull("1"));
    }

}

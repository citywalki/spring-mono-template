package pro.walkin.base.logic.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.walkin.base.domain.user.SysUser;
import pro.walkin.base.domain.user.command.SysUserDelete;
import pro.walkin.framework.api.UseCase;

@Service
@Transactional
@RequiredArgsConstructor
public class SysUserDeleteUseCase implements UseCase<SysUserDelete, Void> {

    @Override
    public Void handle(SysUserDelete command) {
        SysUser.builder().userName(command.getUserKey()).build().deleteById();
        return null;
    }

}

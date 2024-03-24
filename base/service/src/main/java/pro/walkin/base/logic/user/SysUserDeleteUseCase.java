package pro.walkin.base.logic.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.walkin.base.domain.user.command.SysUserDelete;
import pro.walkin.base.logic.user.repository.SysUserRepository;
import pro.walkin.framework.api.UseCase;

@Service
@Transactional
@RequiredArgsConstructor
public class SysUserDeleteUseCase implements UseCase<SysUserDelete, Void> {
    private final SysUserRepository sysUserRepository;

    @Override
    public Void handle(SysUserDelete command) {
        sysUserRepository.deleteById(command.getUserKey());
        return null;
    }

}

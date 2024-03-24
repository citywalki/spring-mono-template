package pro.walkin.base.logic.user.query;

import org.springframework.stereotype.Service;
import pro.walkin.base.domain.user.SysUser;
import pro.walkin.base.logic.user.repository.SysUserRepository;
import pro.walkin.framework.query.AbstractQueries;

@Service
public class SysUserQueries extends AbstractQueries<SysUser, SysUserRepository> {
}

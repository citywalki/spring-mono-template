package pro.walkin.base.logic.user.repository;


import pro.walkin.base.domain.user.QSysUser;
import pro.walkin.base.domain.user.SysUser;
import pro.walkin.framework.jpa.IEntityRepository;

import java.util.Optional;

public interface SysUserRepository extends IEntityRepository<SysUser> {
    Long countByUserNameIgnoreCase(String name);

    Optional<SysUser> findByUserName(String name);

    Optional<SysUser> findByUserNameIgnoreCase(String name);

    default boolean isUserExist(SysUser sysUser) {
        return count(QSysUser.sysUser.eq(sysUser)) > 0;
    }


}

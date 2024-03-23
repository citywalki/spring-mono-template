package pro.walkin.base.logic.user.dao;


import pro.walkin.base.domain.user.SysUser;
import pro.walkin.framework.service.BaseDAO;

import java.util.Optional;

public interface SysUserDAO extends BaseDAO<SysUser> {
    Long countByObjectIdIgnoreCase(String name);

    Optional<SysUser> findByObjectId(String name);

    Optional<SysUser> findByObjectIdIgnoreCase(String name);

}

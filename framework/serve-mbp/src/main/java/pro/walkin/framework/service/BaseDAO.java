package pro.walkin.framework.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pro.walkin.framework.entity.IEntity;

public interface BaseDAO<E extends IEntity> extends BaseMapper<E> {

}

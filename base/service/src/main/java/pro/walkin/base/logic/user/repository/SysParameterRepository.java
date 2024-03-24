package pro.walkin.base.logic.user.repository;

import pro.walkin.base.domain.parameter.QSysParameter;
import pro.walkin.base.domain.parameter.SysParameter;
import pro.walkin.framework.jpa.IEntityRepository;

import java.util.Optional;

import static pro.walkin.base.domain.BaseConstant.PARAMETER_PWD_GENERATOR_POLICY;

public interface SysParameterRepository extends IEntityRepository<SysParameter> {


    default Optional<SysParameter> getForPwdGeneratorPolicy() {
        return findOne(QSysParameter.sysParameter.parameterName.eq(PARAMETER_PWD_GENERATOR_POLICY));
    }

}

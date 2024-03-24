package pro.walkin.base.logic.user.query;

import org.springframework.stereotype.Service;
import pro.walkin.base.domain.parameter.SysParameter;
import pro.walkin.base.logic.user.repository.SysParameterRepository;
import pro.walkin.framework.query.AbstractQueries;

@Service
public class SysParameterQueries extends AbstractQueries<SysParameter, SysParameterRepository> {
}

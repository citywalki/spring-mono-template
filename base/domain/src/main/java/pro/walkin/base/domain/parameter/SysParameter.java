package pro.walkin.base.domain.parameter;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import pro.walkin.framework.entity.BaseEntity;

@Setter
@Getter
@ToString
@SuperBuilder
@Schema(name = "系统参数")
@Entity(name = "SYS_PARAMETER")
@NoArgsConstructor
public class SysParameter extends BaseEntity {

    private String parameterName;

    private String value;

    // public Optional<String> getByParameterName(String parameterName) {
    //     var parameter = selectOne(new LambdaQueryWrapper<SysParameter>()
    //                                       .select(SysParameter::getValue)
    //                                       .eq(SysParameter::getParameterName, parameterName));
    //
    //     return Optional.ofNullable(parameter).map(SysParameter::getValue);
    // }

    // public Optional<String> getForPwdGeneratorPolicy() {
    //     return getByParameterName(PARAMETER_PWD_GENERATOR_POLICY);
    // }

}

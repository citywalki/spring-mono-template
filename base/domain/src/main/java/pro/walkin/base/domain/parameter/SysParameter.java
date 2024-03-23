package pro.walkin.base.domain.parameter;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pro.walkin.framework.entity.BaseEntity;

import java.util.Optional;

import static pro.walkin.base.domain.BaseConstant.PARAMETER_PWD_GENERATOR_POLICY;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Schema(name = "系统参数")
@TableName("SYS_PARAMETER")
@NoArgsConstructor
public class SysParameter extends BaseEntity<SysParameter> {

    @TableField("PARAMETER_NAME")
    private String parameterName;

    @TableField("PARAMETER_VALUE")
    private String value;

    public Optional<String> getByParameterName(String parameterName) {
        var parameter = selectOne(new LambdaQueryWrapper<SysParameter>()
                                          .select(SysParameter::getValue)
                                          .eq(SysParameter::getParameterName, parameterName));

        return Optional.ofNullable(parameter).map(SysParameter::getValue);
    }

    public Optional<String> getForPwdGeneratorPolicy() {
        return getByParameterName(PARAMETER_PWD_GENERATOR_POLICY);
    }

}

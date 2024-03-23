package pro.walkin.framework.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Deprecated
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public abstract class NamedEntity<T extends Panache<T>> extends BaseEntity<T> {

    @Schema(description = "标识空间")
    @TableField(value = "NAMED_SPACE", fill = FieldFill.INSERT_UPDATE)
    private String namedSpace;

    @TableField(value = "OBJECT_ID", fill = FieldFill.INSERT_UPDATE)
    @Schema(description = "对象标识")
    private String objectId;

    @TableField("OBJECT_COMMENT")
    @Schema(description = "对象备注")
    private String comment;

    @TableLogic
    private Integer deleted;

}

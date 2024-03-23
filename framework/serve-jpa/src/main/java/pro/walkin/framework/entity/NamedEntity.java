package pro.walkin.framework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
@Audited
public abstract class NamedEntity extends BaseEntity {

    @Schema(description = "标识空间")
    @Column(name = "NAMED_SPACE", length = 10, nullable = false)
    private String namedSpace;

    @Column(name = "OBJECT_ID", length = 20, nullable = false)
    @Schema(description = "对象标识")
    private String objectId;

    @Column(name = "OBJECT_COMMENT")
    @Schema(description = "对象备注")
    private String comment;

    @JsonIgnore
    @Schema(description = "删除状态, 0:未删除")
    @Builder.Default
    private int delFlag = 0;

    @PrePersist
    public void prePersist() {
        this.namedSpace = "DEFAULT";
    }

}

package pro.walkin.framework.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import static jakarta.persistence.EnumType.STRING;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Audited(withModifiedFlag = true)
public class VersionControl extends BaseEntity {

    @Schema(description = "版本源对象")
    @Column(nullable = false, name = "VERSION_CORRELATION_KEY")
    private String correlationKey;

    @Schema(description = "版本号")
    @Column(nullable = false)
    private long version;

    @Schema(description = "版本状态")
    @Enumerated(STRING)
    @Column(nullable = false)
    @Builder.Default
    private VersionStatus versionStatus = VersionStatus.UNFREEZE;


    public enum VersionStatus {
        UNFREEZE, FREEZE
    }

}

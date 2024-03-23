package pro.walkin.framework.entity;

import jakarta.persistence.Entity;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

@Entity
@RevisionEntity
public class AuditRevisionEntity extends DefaultRevisionEntity {

    private String modifyUser;

}

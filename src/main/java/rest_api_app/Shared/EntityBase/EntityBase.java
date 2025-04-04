package rest_api_app.Shared.EntityBase;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class EntityBase {
    @Column(name = "CREATED_AT", updatable = true)
    public Date createdAt;
    @Column(name = "CREATED_BY", nullable = true)
    public int createBy;
    @Column(name = "MODIFIED_AT", nullable = true)
    public Date modifiedAt;
    @Column(name = "MODIFIED_BY", nullable = true)
    public int modifiedBy;
    @Column(name = "DELETED", nullable = true)
    public boolean deleted;

    @PrePersist
    public void onPrePersist() {
        this.createdAt = new Date();
    }

    @PreUpdate
    public void onPreUpdate() {
        this.modifiedAt = new Date();
    }
}

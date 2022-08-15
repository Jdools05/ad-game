package db.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "verification_links")
public class VerificationLinkEntity extends PanacheEntity {

    public VerificationLinkEntity() {
        createdAt = LocalDate.now();
    }

    @Column(name = "verification_timestamp")
    public LocalDate createdAt;

    @Column
    public String key;

    @OneToOne
    public UserEntity userEntity;

    public static VerificationLinkEntity findByKey (String key) {
        return find("key", key).firstResult();
    }

    public long getLifespanInDays() {
        return LocalDate.now().toEpochDay() - createdAt.toEpochDay();
    }
}

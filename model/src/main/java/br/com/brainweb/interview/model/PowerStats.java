package br.com.brainweb.interview.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(schema = "interview_service")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = PowerStats.class)
public class PowerStats implements Serializable {

    @Id
    @GeneratedValue
    @Nullable
    private UUID id;

    @NonNull
    private Integer strength;

    @NonNull
    private Integer agility;

    @NonNull
    private Integer dexterity;

    @NonNull
    private Integer intelligence;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    public PowerStats(@Nullable String id) {
        this.id = UUID.fromString(id);
    }

    public PowerStats(@Nullable UUID id) {
        this.id = id;
    }
}

package br.com.brainweb.interview.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(schema = "interview_service")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Hero implements Serializable {

    @Id
    @GeneratedValue
    @Nullable
    private UUID id;

    @NonNull
    private String name;

    @NonNull
    private String race;

    @NonNull
    private Boolean enabled;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH, optional = false)
    @JoinColumn(name = "power_stats_id")
    @JsonIdentityReference(alwaysAsId = true)
    private PowerStats powerStats;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @JsonProperty("powerStats")
    public void setPowerStats(String id) {
        this.powerStats = new PowerStats(id);
    }
}
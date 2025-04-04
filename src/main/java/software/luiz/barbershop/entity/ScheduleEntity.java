package software.luiz.barbershop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(
        name = "SCHEDULES",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_SCHEDULE_INTERVAL", columnNames = {"start_at", "end_at"}),
        }
)
@Getter
@Setter
@ToString
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name= "start_at")
    private OffsetDateTime startAt;

    @Column(nullable = false, name= "end_at")
    private OffsetDateTime endAt;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client = new ClientEntity();

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ScheduleEntity that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getStartAt(), that.getStartAt()) && Objects.equals(getEndAt(), that.getEndAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStartAt(), getEndAt());
    }
}

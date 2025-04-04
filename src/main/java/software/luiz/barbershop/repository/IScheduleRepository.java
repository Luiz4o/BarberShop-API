package software.luiz.barbershop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import software.luiz.barbershop.entity.ScheduleEntity;

import java.time.OffsetDateTime;
import java.util.List;

public interface IScheduleRepository extends JpaRepository<ScheduleEntity,Long> {

    List<ScheduleEntity> findByStartAtGreaterThanEqualAndEndAtLessThanEqualOrderByStartAtAscEndAtAsc(
            final OffsetDateTime startAt,
            final OffsetDateTime endAt
    );

    boolean existsByStartAtAndEndAt(final OffsetDateTime startAt, final OffsetDateTime endAt);

}

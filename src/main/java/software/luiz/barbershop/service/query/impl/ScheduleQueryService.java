package software.luiz.barbershop.service.query.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.luiz.barbershop.entity.ScheduleEntity;
import software.luiz.barbershop.exception.NotFoundException;
import software.luiz.barbershop.exception.ScheduleInUseException;
import software.luiz.barbershop.service.query.IScheduleQueryService;
import software.luiz.barbershop.repository.IScheduleRepository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class ScheduleQueryService implements IScheduleQueryService {

    @Autowired
    private final IScheduleRepository repository;

    @Override
    public ScheduleEntity findById(long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Agendamento não encontrado"));
    }

    @Override
    public List<ScheduleEntity> findInMonth(OffsetDateTime startAt, OffsetDateTime endAt) {
        return repository.findByStartAtGreaterThanEqualAndEndAtLessThanEqualOrderByStartAtAscEndAtAsc(startAt, endAt);
    }

    @Override
    public void verifyIfScheduleExists(OffsetDateTime startAt, OffsetDateTime endAt) {
        if ( repository.existsByStartAtAndEndAt(startAt, endAt)){
            var message = "Já existe um cliente agendado no horário solicitado";
            throw new ScheduleInUseException(message);
        }
    }
}

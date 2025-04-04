package software.luiz.barbershop.service;

import software.luiz.barbershop.entity.ScheduleEntity;

public interface IScheduleService {

    ScheduleEntity save(final ScheduleEntity entity);

    void delete(final long id);
}

package software.luiz.barbershop.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import software.luiz.barbershop.entity.ScheduleEntity;
import software.luiz.barbershop.repository.IScheduleRepository;
import software.luiz.barbershop.service.IScheduleService;
import software.luiz.barbershop.service.query.IScheduleQueryService;

@Service
public class ScheduleService implements IScheduleService {

    private final IScheduleRepository repository;
    private final IScheduleQueryService queryService;

    @Override
    public ScheduleEntity save(ScheduleEntity entity) {
        queryService.verifyIfScheduleExists(entity.getStartAt(), entity.getEndAt());

        return  repository.save(entity);
    }

    @Override
    public void delete(long id) {
        queryService.findById(id);
        repository.deleteById(id);

    }

    public ScheduleService(IScheduleRepository repository, IScheduleQueryService queryService) {
        this.repository = repository;
        this.queryService = queryService;
    }
}

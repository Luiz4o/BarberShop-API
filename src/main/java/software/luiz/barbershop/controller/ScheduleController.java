package software.luiz.barbershop.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import software.luiz.barbershop.controller.request.SaveScheduleRequest;
import software.luiz.barbershop.controller.response.SaveScheduleResponse;
import software.luiz.barbershop.controller.response.ScheduleAppointmentMonthResponse;
import software.luiz.barbershop.mapper.IScheduleMapper;
import software.luiz.barbershop.service.IScheduleService;
import software.luiz.barbershop.service.query.IScheduleQueryService;

import java.time.YearMonth;
import java.time.ZoneOffset;

@RestController
@RequestMapping("schedules")
public class ScheduleController {

    private final IScheduleService service;
    private final IScheduleQueryService queryService;
    private final IScheduleMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    SaveScheduleResponse save(@RequestBody @Valid SaveScheduleRequest request){
        var entity = mapper.toEntity(request);
        service.save(entity);
        return mapper.toSaveResponse(entity);

    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable final long id){
        service.delete(id);
    }

    @GetMapping("{year}/{month}")
    ScheduleAppointmentMonthResponse listMont(@PathVariable final int year,@PathVariable final int month){
        var yearMonth= YearMonth.of(year, month);
        var startAt = yearMonth.atDay(1)
                .atTime(0,0,0,0)
                .atOffset(ZoneOffset.UTC);
        var endAt = yearMonth.atEndOfMonth()
                .atTime(23,59,59,999_999_99)
                .atOffset(ZoneOffset.UTC);

        var entities= queryService.findInMonth(startAt,endAt);
        return  mapper.toMonthResponse(year,month,entities);
    }



    public ScheduleController(IScheduleService service, IScheduleQueryService queryService, IScheduleMapper mapper) {
        this.service = service;
        this.queryService = queryService;
        this.mapper = mapper;
    }
}

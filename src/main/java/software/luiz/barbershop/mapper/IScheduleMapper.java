package software.luiz.barbershop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import software.luiz.barbershop.controller.request.SaveScheduleRequest;
import software.luiz.barbershop.controller.response.ClientScheduleAppointmentResponse;
import software.luiz.barbershop.controller.response.SaveScheduleResponse;
import software.luiz.barbershop.controller.response.ScheduleAppointmentMonthResponse;
import software.luiz.barbershop.entity.ScheduleEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IScheduleMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client.id", source = "clientId")
    ScheduleEntity toEntity(final SaveScheduleRequest request);

    @Mapping(target = "clientId", source = "client.id")
    SaveScheduleResponse toSaveResponse(final ScheduleEntity entity);

    @Mapping(target = "scheduledAppointments", expression = "java(toClientMonthResponse(entities))")
    ScheduleAppointmentMonthResponse toMonthResponse(final int year, final int month, final List<ScheduleEntity> entities);

    List<ClientScheduleAppointmentResponse> toClientMonthResponse(final List<ScheduleEntity> entities);

    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "clientName", source = "client.name")
    @Mapping(target = "day", expression = "java(entity.getStartAt().getDayOfMonth())")
    ClientScheduleAppointmentResponse toClientMonthResponse(final ScheduleEntity entity);
}

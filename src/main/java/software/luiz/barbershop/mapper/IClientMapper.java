package software.luiz.barbershop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import software.luiz.barbershop.controller.request.SaveClientRequest;
import software.luiz.barbershop.controller.request.UpdateClientRequest;
import software.luiz.barbershop.controller.response.ClientDetailResponse;
import software.luiz.barbershop.controller.response.ListClientResponse;
import software.luiz.barbershop.controller.response.SaveClientResponse;
import software.luiz.barbershop.controller.response.UpdateClientResponse;
import software.luiz.barbershop.entity.ClientEntity;


import java.util.List;

@Mapper(componentModel = "spring")
public interface IClientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    ClientEntity toEntity(final SaveClientRequest request);

    SaveClientResponse toSaveResponse(final ClientEntity entity);

    @Mapping(target = "schedules", ignore = true)
    ClientEntity toEntity(final long id, final UpdateClientRequest request);

    UpdateClientResponse toUpdateResponse(final ClientEntity entity);

    ClientDetailResponse toDetailResponse(final ClientEntity entity);

    List<ListClientResponse> toListResponse(final List<ClientEntity> entities);

}

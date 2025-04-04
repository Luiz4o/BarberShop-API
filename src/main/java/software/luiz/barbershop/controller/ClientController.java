package software.luiz.barbershop.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import software.luiz.barbershop.controller.request.SaveClientRequest;
import software.luiz.barbershop.controller.request.UpdateClientRequest;
import software.luiz.barbershop.controller.response.ClientDetailResponse;
import software.luiz.barbershop.controller.response.ListClientResponse;
import software.luiz.barbershop.controller.response.SaveClientResponse;
import software.luiz.barbershop.controller.response.UpdateClientResponse;
import software.luiz.barbershop.mapper.IClientMapper;
import software.luiz.barbershop.service.IClientService;
import software.luiz.barbershop.service.query.IClientQueryService;

import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientController {

    private final IClientService service;
    private final IClientMapper mapper;
    private final IClientQueryService queryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    SaveClientResponse save(@RequestBody @Valid final SaveClientRequest request){
        var entity = mapper.toEntity(request);
        service.save(entity);

        return mapper.toSaveResponse(entity);
    }

    @PutMapping("{id}")
    UpdateClientResponse update(@PathVariable final long id, @RequestBody @Valid final UpdateClientRequest request){
        var entity = mapper.toEntity(id, request);
        service.update(entity);
        return  mapper.toUpdateResponse(entity);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable final long id){
        service.delete(id);
    }

    @GetMapping("{id}")
    ClientDetailResponse findById(@PathVariable final long id){
        var entity = queryService.findById(id);
        return mapper.toDetailResponse(entity);
    }

    @GetMapping
    List<ListClientResponse> list(){
        var entities = queryService.list();
        return  mapper.toListResponse(entities);
    }


    public ClientController(IClientService service, IClientMapper mapper, IClientQueryService queryService) {
        this.service = service;
        this.mapper = mapper;
        this.queryService = queryService;
    }
}

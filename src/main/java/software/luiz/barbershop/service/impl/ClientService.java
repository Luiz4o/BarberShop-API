package software.luiz.barbershop.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.luiz.barbershop.entity.ClientEntity;
import software.luiz.barbershop.repository.IClientRepository;
import software.luiz.barbershop.service.IClientService;
import software.luiz.barbershop.service.query.IClientQueryService;

@Repository
public class ClientService implements IClientService {

    private final IClientRepository repository;
    private final IClientQueryService queryService;

    @Override
    public ClientEntity save(ClientEntity entity) {
        queryService.verifyEmail(entity.getEmail());
        queryService.verifyPhone(entity.getPhone());
        return repository.save(entity);
    }

    @Override
    public ClientEntity update(ClientEntity entity) {
        queryService.verifyEmail(entity.getId(), entity.getEmail());
        queryService.verifyPhone(entity.getId(), entity.getPhone());

        var stored = queryService.findById(entity.getId());
        stored.setEmail(entity.getEmail());
        stored.setPhone(entity.getPhone());
        stored.setName(entity.getName());

        return repository.save(stored);
    }

    @Override
    public void delete(long id) {
        queryService.findById(id);
        repository.deleteById(id);
    }

    public ClientService(IClientRepository repository, IClientQueryService queryService) {
        this.repository = repository;
        this.queryService = queryService;
    }
}

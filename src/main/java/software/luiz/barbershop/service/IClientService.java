package software.luiz.barbershop.service;

import software.luiz.barbershop.entity.ClientEntity;

public interface IClientService {

    ClientEntity save(final ClientEntity entity);

    ClientEntity update(final ClientEntity entity);

    void delete(final long id);
}

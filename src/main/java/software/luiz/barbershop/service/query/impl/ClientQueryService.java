package software.luiz.barbershop.service.query.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.luiz.barbershop.entity.ClientEntity;
import software.luiz.barbershop.exception.EmailInUseException;
import software.luiz.barbershop.exception.NotFoundException;
import software.luiz.barbershop.exception.PhoneInUseException;
import software.luiz.barbershop.service.query.IClientQueryService;
import software.luiz.barbershop.repository.IClientRepository;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ClientQueryService implements IClientQueryService {

    @Autowired
    private final IClientRepository repository;

    @Override
    public ClientEntity findById(long id) {
        return  repository.findById(id).orElseThrow(() -> new NotFoundException("Não foi encontrado o cliente de ID" + id));
    }

    @Override
    public List<ClientEntity> list() {
        return repository.findAll();
    }

    @Override
    public void verifyPhone(String phone) {
        if(repository.existsByPhone(phone)) {
            var message = "O telefone" + phone + "já esta em uso";
            throw  new PhoneInUseException(message);
        }
    }

    @Override
    public void verifyPhone(long id, String phone) {
        var optional = repository.findByPhone(phone);
        if(optional.isPresent() && !Objects.equals(optional.get().getPhone(),phone)) {
            var message = "O telefone" + phone + "já esta em uso";
            throw  new PhoneInUseException(message);
        }
    }

    @Override
    public void verifyEmail(String email) {
        if(repository.existsByEmail(email)) {
            var message = "O e-mail" + email + "já esta em uso";
            throw  new EmailInUseException(message);
        }
    }

    @Override
    public void verifyEmail(long id, String email) {
        var optional = repository.findByEmail(email);
        if(optional.isPresent() && !Objects.equals(optional.get().getEmail(),email)) {
            var message = "O e-mail" + email + "já esta em uso";
            throw  new EmailInUseException(message);
        }
    }
}

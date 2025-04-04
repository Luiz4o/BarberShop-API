package software.luiz.barbershop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import software.luiz.barbershop.entity.ClientEntity;

import java.util.Optional;

@Repository
public interface IClientRepository extends JpaRepository<ClientEntity, Long> {

    boolean existsByEmail(final String email);

    Optional<ClientEntity> findByEmail(final String email);

    boolean existsByPhone(final String phone);

    Optional<ClientEntity> findByPhone(final String phone);

}

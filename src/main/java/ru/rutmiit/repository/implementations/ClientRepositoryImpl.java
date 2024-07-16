package ru.rutmiit.repository.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.rutmiit.domain.Client;
import ru.rutmiit.domain.Payment;
import ru.rutmiit.repository.BaseRepository;
import ru.rutmiit.repository.ClientRepository;

@Repository
public class ClientRepositoryImpl extends BaseRepository<Client, Integer> implements ClientRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public ClientRepositoryImpl(Class<Client> clientClass) {
        super(clientClass);
    }


}

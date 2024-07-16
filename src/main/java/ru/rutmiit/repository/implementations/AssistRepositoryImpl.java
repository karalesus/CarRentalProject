package ru.rutmiit.repository.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.rutmiit.domain.Assist;
import ru.rutmiit.repository.AssistRepository;
import ru.rutmiit.repository.BaseRepository;

import java.util.List;

@Repository
public class AssistRepositoryImpl extends BaseRepository<Assist, Integer> implements AssistRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public AssistRepositoryImpl(Class<Assist> assistClass) {
        super(assistClass);
    }

    @Override
    public List<Assist> findAllAssists() {
        return entityManager.createQuery("SELECT a FROM Assist a", Assist.class).getResultList();
    }
}

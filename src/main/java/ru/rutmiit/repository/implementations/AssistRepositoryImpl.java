package ru.rutmiit.repository.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.rutmiit.domain.Assist;
import ru.rutmiit.repository.AssistRepository;
import ru.rutmiit.repository.BaseRepository;

import java.util.Optional;

@Repository
public class AssistRepositoryImpl extends BaseRepository<Assist, Integer> implements AssistRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public AssistRepositoryImpl(Class<Assist> assistClass) {
        super(assistClass);
    }

    @Override
    public Optional<Assist> findByName(String name) {
        try {
            Assist assist = entityManager.createQuery("SELECT a FROM Assist a WHERE a.name = :name", Assist.class)
                    .setParameter("name", name)
                    .getSingleResult();
            return Optional.of(assist);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}

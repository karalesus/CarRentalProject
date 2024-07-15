package ru.rutmiit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rutmiit.domain.Assist;

import java.util.Optional;

@Repository
public interface AssistRepository {

    Optional<Assist> findByName(String name);
}

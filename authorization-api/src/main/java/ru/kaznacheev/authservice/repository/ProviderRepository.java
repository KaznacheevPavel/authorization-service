package ru.kaznacheev.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kaznacheev.authservice.model.entity.ProviderEntity;

@Repository
public interface ProviderRepository extends JpaRepository<ProviderEntity, Integer> {

    ProviderEntity findByTitle(String title);
    
}

package ru.kaznacheev.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kaznacheev.authservice.model.entity.UserProviderEntity;
import ru.kaznacheev.authservice.model.entity.UserProviderPrimaryKey;

import java.util.Optional;

@Repository
public interface UserProviderRepository extends JpaRepository<UserProviderEntity, UserProviderPrimaryKey> {

    Optional<UserProviderEntity> findByProviderUserId(String providerUserId);
    
}

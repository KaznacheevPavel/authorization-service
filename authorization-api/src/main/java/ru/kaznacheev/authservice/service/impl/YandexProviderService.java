package ru.kaznacheev.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kaznacheev.authservice.model.AuthorizedUser;
import ru.kaznacheev.authservice.model.entity.ProviderEntity;
import ru.kaznacheev.authservice.model.entity.UserEntity;
import ru.kaznacheev.authservice.model.entity.UserProviderEntity;
import ru.kaznacheev.authservice.model.entity.UserProviderPrimaryKey;
import ru.kaznacheev.authservice.repository.ProviderRepository;
import ru.kaznacheev.authservice.repository.UserProviderRepository;
import ru.kaznacheev.authservice.repository.UserRepository;
import ru.kaznacheev.authservice.service.OAuth2ProviderService;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class YandexProviderService implements OAuth2ProviderService {

    private final UserProviderRepository userProviderRepository;
    private final ProviderRepository providerRepository;
    private final UserRepository userRepository;

    @Override
    public String getProviderName() {
        return "yandex";
    }

    @Transactional
    @Override
    public AuthorizedUser save(OAuth2User oAuth2User) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<UserProviderEntity> userProviderEntity = userProviderRepository.findByProviderUserId(oAuth2User.getAttribute("id"));
        if (authentication == null || !authentication.isAuthenticated()) {
            if (userProviderEntity.isEmpty()) {
                UserEntity user = UserEntity.builder()
                        .email(oAuth2User.getName())
                        .userProviders(new HashSet<>())
                        .build();
                ProviderEntity provider = providerRepository.findByTitle(getProviderName());
                UserProviderEntity userProvider = UserProviderEntity.builder()
                        .primaryKey(new UserProviderPrimaryKey())
                        .providerUserId(oAuth2User.getAttribute("id"))
                        .user(user)
                        .provider(provider)
                        .build();
                user.addProvider(userProvider);
                userRepository.save(user);
            } else {
                UserEntity user = userProviderEntity.get().getUser();
                user.setEmail(oAuth2User.getName());
            }
            return new AuthorizedUser(oAuth2User.getName(), "", Collections.emptyList());
        }
        throw new OAuth2AuthenticationException("You are already logged in");
    }

}

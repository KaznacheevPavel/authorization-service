package ru.kaznacheev.authservice.service;

import org.springframework.security.oauth2.core.user.OAuth2User;
import ru.kaznacheev.authservice.model.entity.AuthorizedUser;

public interface OAuth2ProviderService {

    String getProviderName();

    AuthorizedUser save(OAuth2User oAuth2User);

}

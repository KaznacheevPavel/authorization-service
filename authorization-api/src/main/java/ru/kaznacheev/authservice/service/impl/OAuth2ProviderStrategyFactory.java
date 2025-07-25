package ru.kaznacheev.authservice.service.impl;

import org.springframework.stereotype.Service;
import ru.kaznacheev.authservice.service.OAuth2ProviderService;
import ru.kaznacheev.authservice.service.StrategyFactory;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OAuth2ProviderStrategyFactory implements StrategyFactory<OAuth2ProviderService> {

    private final Map<String, OAuth2ProviderService> strategies;

    public OAuth2ProviderStrategyFactory(List<OAuth2ProviderService> providers) {
        strategies = providers.stream().collect(Collectors.toMap(
                OAuth2ProviderService::getProviderName,
                Function.identity()
        ));
    }

    @Override
    public OAuth2ProviderService getStrategy(String providerName) {
        return Optional.ofNullable(strategies.get(providerName))
                .orElseThrow(() -> new IllegalArgumentException("Неизвестное имя OAuth2 провайдера: " + providerName));
    }

}

package ru.kaznacheev.authservice.service;

public interface StrategyFactory<T> {

    T getStrategy(String strategyName);

}

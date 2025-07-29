CREATE TABLE IF NOT EXISTS user_providers (
    user_id UUID NOT NULL,
    provider_id INTEGER NOT NULL,
    provider_user_id VARCHAR(64) NOT NULL UNIQUE,
    PRIMARY KEY (user_id, provider_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (provider_id) REFERENCES providers(provider_id)
);

COMMENT ON TABLE user_providers IS 'Связь пользователя и OAuth2 провайдера';
COMMENT ON COLUMN user_providers.user_id IS 'Идентификатор пользователя';
COMMENT ON COLUMN user_providers.provider_id IS 'Идентификатор OAuth2 провайдера';
COMMENT ON COLUMN user_providers.provider_user_id IS 'Идентификатор пользователя у OAuth2 провайдера';

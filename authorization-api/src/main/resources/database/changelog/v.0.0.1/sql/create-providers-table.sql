CREATE TABLE IF NOT EXISTS providers (
    provider_id SERIAL NOT NULL UNIQUE,
    title VARCHAR(32) NOT NULL UNIQUE,
    PRIMARY KEY (provider_id)
);

COMMENT ON TABLE providers IS 'OAuth2 провайдеры';
COMMENT ON COLUMN providers.provider_id IS 'Идентификатор провайдера';
COMMENT ON COLUMN providers.title IS 'Название провайдера';

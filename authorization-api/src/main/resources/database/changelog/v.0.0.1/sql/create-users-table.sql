CREATE TABLE IF NOT EXISTS users (
    user_id UUID NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255),
    PRIMARY KEY (user_id)
);

COMMENT ON TABLE users IS 'Пользователи';
COMMENT ON COLUMN users.user_id IS 'Идентификатор пользователя';
COMMENT ON COLUMN users.email IS 'Логин пользователя';
COMMENT ON COLUMN users.password_hash IS 'Хэш пароля пользователя';
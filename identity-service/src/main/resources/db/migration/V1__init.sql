CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    password_hash TEXT,
    google_id TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

CREATE TABLE auth_providers (
    name VARCHAR(255) PRIMARY KEY,
    client_id VARCHAR(255),
    client_secret VARCHAR(255)
);

CREATE TABLE user_auth_providers (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    auth_provider_name TEXT NOT NULL,
    access_token VARCHAR(255),
    refresh_token VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (auth_provider_name) REFERENCES auth_providers (name) ON DELETE CASCADE
);




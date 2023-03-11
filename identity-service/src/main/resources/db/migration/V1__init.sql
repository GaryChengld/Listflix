CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
    id UUID DEFAULT uuid_generate_v4() GENERATED ALWAYS PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    email varchar(255) NOT NULL UNIQUE,
    password_hash varchar(255),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

CREATE TABLE social_providers (
    id SERIAL PRIMARY KEY,
    provider_name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE social_accounts (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
    provider_id INTEGER REFERENCES social_providers(id) ON DELETE CASCADE,
    user_reference_id VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE login_sessions (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
    token TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    expired_at TIMESTAMP NOT NULL,
    source VARCHAR(255) NOT NULL
);



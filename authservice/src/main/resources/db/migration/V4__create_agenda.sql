CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE agenda (
                        id UUID PRIMARY KEY,
                        titulo VARCHAR(255) NOT NULL,
                        descricao TEXT,
                        data DATE,
                        status_agenda VARCHAR(50)
);
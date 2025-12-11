CREATE TABLE IF NOT EXISTS usuario (
                         id UUID PRIMARY KEY,
                         username VARCHAR(255) NOT NULL UNIQUE,
                         password VARCHAR(255) NOT NULL,
                         email VARCHAR(255) NOT NULL UNIQUE,
                         descricao TEXT
);

CREATE TABLE IF NOT EXISTS agenda (
                        id UUID PRIMARY KEY,
                        nome VARCHAR(255) NOT NULL,
                        descricao TEXT,
                        status VARCHAR(20) NOT NULL DEFAULT 'ATIVA',
                        usuario_id UUID NOT NULL REFERENCES usuario(id),
                        tipo_agendamento VARCHAR(50),
                        tipo_repeticao VARCHAR(50),
                        whatssap VARCHAR(50)
                        horario_envio DATE,
                        enviar_agora BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS lembrete (
                          id UUID PRIMARY KEY,
                          titulo VARCHAR(255) NOT NULL,
                          tipo VARCHAR(20) NOT NULL,
                          data_hora TIMESTAMP NOT NULL,
                          status VARCHAR(20) NOT NULL DEFAULT 'PENDENTE',
                          agenda_id UUID NOT NULL REFERENCES agenda(id)
);

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
                        status VARCHAR(20),
                        sys_user UUID NOT NULL REFERENCES usuario(id),
                        tipo_agendamento VARCHAR(50),
                        tipo_repeticao VARCHAR(50),
                        whatssap VARCHAR(50),
                        horario_envio DATE,
                        enviar_agora BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS lembrete (
                          id UUID PRIMARY KEY,
                          titulo VARCHAR(255),
                          tipo VARCHAR(20),
                          data_hora TIMESTAMP ,
                          status VARCHAR(20),
                          agenda_id UUID REFERENCES agenda(id)
);

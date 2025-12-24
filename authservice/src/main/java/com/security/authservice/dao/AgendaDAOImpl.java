package com.security.authservice.dao;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.security.authservice.dto.AgendaProjection;
import com.security.authservice.entity.Agenda;
import com.security.authservice.entity.QAgenda;
import com.security.authservice.entity.QSysUser;
import com.security.authservice.enums.StatusAgenda;
import com.security.authservice.id.Notificador;
import com.security.authservice.security.context.ServiceContext;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AgendaDAOImpl implements AgendaDAO {

    private static final QAgenda agenda = QAgenda.agenda;
    private static final QSysUser sysUser = QSysUser.sysUser;

    private final Map<String, Notificador> notificador;

    @PostConstruct
    void init() {
        System.out.println("Iniciando NotificadorEmail" + notificador.get("notificadorEmail"));
    }

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public List<AgendaProjection> getAgenda() {
        final var projections = Projections
                .bean(AgendaProjection.class,
                        agenda.id,
                        agenda.descricao,
                        agenda.sysUser.username.as("nomeUsuario"),
                        agenda.nome,
                        agenda.status
                );

        return new JPAQueryFactory(em)
                .select(projections)
                .from(agenda)
                .leftJoin(agenda.sysUser, sysUser)
                .where(sysUser.username.eq(ServiceContext.getUser()))
                .orderBy(agenda.id.desc())
                .fetch();
    }
}

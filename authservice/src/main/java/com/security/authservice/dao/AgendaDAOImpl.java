package com.security.authservice.dao;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.security.authservice.dto.AgendaProjection;
import com.security.authservice.entity.QAgenda;
import com.security.authservice.entity.QSysUser;
import com.security.authservice.security.context.ServiceContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AgendaDAOImpl implements AgendaDAO {

    private static final QAgenda agenda = QAgenda.agenda;
    private static final QSysUser sysUser = QSysUser.sysUser;

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public List<AgendaProjection> getAgenda() {
    final var projections = Projections
                .bean(AgendaProjection.class,
                agenda.descricao,
                agenda.sysUser.username,
                agenda.nome,
                agenda.status
        );

        return new JPAQueryFactory(em)
                .select(projections)
                .from(agenda)
                .leftJoin(agenda.sysUser, sysUser)
                .where(sysUser.username.eq(ServiceContext.getUser()))
                .fetch();
    }
}

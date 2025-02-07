package com.vinidev.agendamento_notificacao_api.infrastruture.repositories;

import com.vinidev.agendamento_notificacao_api.infrastruture.entities.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}

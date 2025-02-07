package com.vinidev.agendamento_notificacao_api.business;

import com.vinidev.agendamento_notificacao_api.business.mapper.IAgendamentoMapper;
import com.vinidev.agendamento_notificacao_api.controller.dto.in.AgendamentoRequestDto;
import com.vinidev.agendamento_notificacao_api.controller.dto.out.AgendamentoResponseDto;
import com.vinidev.agendamento_notificacao_api.infrastruture.repositories.AgendamentoRepository;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {

    private final AgendamentoRepository repository;
    private final IAgendamentoMapper agendamentoMapper;

    public AgendamentoService(AgendamentoRepository repository, IAgendamentoMapper agendamentoMapper) {
        this.repository = repository;
        this.agendamentoMapper = agendamentoMapper;
    }

    public AgendamentoResponseDto gerarAgendamento(AgendamentoRequestDto dto) {
        var agendamento = agendamentoMapper.paraEntity(dto);
        repository.save(agendamento);
        return agendamentoMapper.paraDto(agendamento);
    }


}

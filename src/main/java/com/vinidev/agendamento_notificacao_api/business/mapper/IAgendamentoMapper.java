package com.vinidev.agendamento_notificacao_api.business.mapper;

import com.vinidev.agendamento_notificacao_api.controller.dto.in.AgendamentoRequestDto;
import com.vinidev.agendamento_notificacao_api.controller.dto.out.AgendamentoResponseDto;
import com.vinidev.agendamento_notificacao_api.infrastruture.entities.Agendamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IAgendamentoMapper {

    Agendamento paraEntity(AgendamentoRequestDto agendamento);
    AgendamentoResponseDto paraDto(Agendamento agendamento);


    @Mapping(target = "dataHoraModificacao", expression = "java(LocalDateTime.now())")
    @Mapping(target = "statusNotificacao", expression = "java(StatusNotificacao.CANCELADO)")
    Agendamento paraEntityCancelamento(Agendamento agendamento);
}

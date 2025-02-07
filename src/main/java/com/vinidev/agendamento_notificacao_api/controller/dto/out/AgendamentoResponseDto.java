package com.vinidev.agendamento_notificacao_api.controller.dto.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vinidev.agendamento_notificacao_api.infrastruture.enums.StatusNotificacao;

import java.time.LocalDateTime;

public record AgendamentoResponseDto(Long id,
                                     String emailDestinatario,
                                     String telefoneDestinatario,
                                     String mensagem,
                                     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                                    LocalDateTime dataHoraEnvio,
                                     StatusNotificacao statusNotificacao) {
}

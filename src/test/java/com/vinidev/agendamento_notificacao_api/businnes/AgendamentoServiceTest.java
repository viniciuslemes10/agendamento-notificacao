package com.vinidev.agendamento_notificacao_api.businnes;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.vinidev.agendamento_notificacao_api.business.AgendamentoService;
import com.vinidev.agendamento_notificacao_api.business.mapper.IAgendamentoMapper;
import com.vinidev.agendamento_notificacao_api.controller.dto.in.AgendamentoRequestDto;
import com.vinidev.agendamento_notificacao_api.controller.dto.out.AgendamentoResponseDto;
import com.vinidev.agendamento_notificacao_api.infrastruture.entities.Agendamento;
import com.vinidev.agendamento_notificacao_api.infrastruture.enums.StatusNotificacao;
import com.vinidev.agendamento_notificacao_api.infrastruture.repositories.AgendamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AgendamentoServiceTest {
    @InjectMocks
    AgendamentoService agendamentoService;

    @Mock
    private AgendamentoRepository repository;

    @Mock
    private IAgendamentoMapper agendamentoMapper;

    private AgendamentoRequestDto requestDto;
    private AgendamentoResponseDto responseDto;
    private Agendamento agendamentoEntity;

    @BeforeEach
    void setUp() {
        agendamentoEntity = new Agendamento(1L, "email@gmail.com", "119080356",
                LocalDateTime.of(2025, 03, 02, 13, 01, 01),
                LocalDateTime.now(), null,
                "Testando a requisição esperando uma resposta",
                StatusNotificacao.AGENDADO);

        requestDto = new AgendamentoRequestDto("email@gmail.com", "119080356",
                "Testando a requisição esperando uma resposta",
                LocalDateTime.of(2025, 03, 02, 13, 01, 01));

        responseDto = new AgendamentoResponseDto(1L, "email@gmail.com", "119080356",
                "Testando a requisição esperando uma resposta",
                LocalDateTime.of(2025, 03, 02, 13, 01, 01),
                StatusNotificacao.AGENDADO);
    }

    @Test
    void devGravarAgendamento() {
        when(agendamentoMapper.paraEntity(requestDto)).thenReturn(agendamentoEntity);
        when(repository.save(agendamentoEntity)).thenReturn(agendamentoEntity);
        when(agendamentoMapper.paraDto(agendamentoEntity)).thenReturn(responseDto);

        AgendamentoResponseDto agendamentoResponseDto = agendamentoService.gerarAgendamento(requestDto);

        verify(agendamentoMapper, times(1)).paraEntity(requestDto);
        verify(repository, times(1)).save(agendamentoEntity);
        verify(agendamentoMapper, times(1)).paraDto(agendamentoEntity);

        assertThat(agendamentoResponseDto).usingRecursiveAssertion().isEqualTo(responseDto);
    }
}

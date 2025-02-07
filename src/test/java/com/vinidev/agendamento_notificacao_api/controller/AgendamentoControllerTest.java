package com.vinidev.agendamento_notificacao_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.vinidev.agendamento_notificacao_api.business.AgendamentoService;
import com.vinidev.agendamento_notificacao_api.controller.dto.in.AgendamentoRequestDto;
import com.vinidev.agendamento_notificacao_api.controller.dto.out.AgendamentoResponseDto;
import com.vinidev.agendamento_notificacao_api.infrastruture.enums.StatusNotificacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AgendamentoControllerTest {

    @InjectMocks
    AgendamentoController agendamentoController;

    @Mock
    AgendamentoService agendamentoService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    private AgendamentoRequestDto requestDto;
    private AgendamentoResponseDto responseDto;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(agendamentoController).build();

        objectMapper.registerModule(new JavaTimeModule());

        requestDto = new AgendamentoRequestDto("email@gmail.com", "119080356",
                "Testando a requisição esperando uma resposta", LocalDateTime.of(2025, 03, 02, 13, 01, 01));

        responseDto = new AgendamentoResponseDto(1L, "email@gmail.com", "119080356",
                "Testando a requisição esperando uma resposta", LocalDateTime.of(2025, 03, 02, 13, 01, 01),
                StatusNotificacao.AGENDADO);
    }

    @Test
    void deveCriarAgendamentoComSucesso() throws Exception {
        when(agendamentoService.gerarAgendamento(requestDto)).thenReturn(responseDto);

        mockMvc.perform(post("/api/v1/agendamento")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.emailDestinatario").value("email@gmail.com"))
                .andExpect(jsonPath("$.telefoneDestinatario").value(responseDto.telefoneDestinatario()))
                .andExpect(jsonPath("$.mensagem").value(responseDto.mensagem()))
                .andExpect(jsonPath("$.dataHoraEnvio").exists())
                .andExpect(jsonPath("$.statusNotificacao").value("AGENDADO"));

        verify(agendamentoService, times(1)).gerarAgendamento(requestDto);
    }
}

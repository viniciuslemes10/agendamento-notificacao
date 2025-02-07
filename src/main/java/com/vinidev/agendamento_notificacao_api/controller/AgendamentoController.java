package com.vinidev.agendamento_notificacao_api.controller;

import com.vinidev.agendamento_notificacao_api.business.AgendamentoService;
import com.vinidev.agendamento_notificacao_api.controller.dto.in.AgendamentoRequestDto;
import com.vinidev.agendamento_notificacao_api.controller.dto.out.AgendamentoResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/agendamento")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponseDto> gerarAgendamento(@RequestBody AgendamentoRequestDto dto) {
        var response = agendamentoService.gerarAgendamento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDto> buscarAgendamento(@PathVariable("id") Long id){
        var response = agendamentoService.buscarAgendamento(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelaAgendamento(@PathVariable("id") Long id) {
        agendamentoService.cancelarAgendamento(id);
        return ResponseEntity.accepted().build();
    }
}

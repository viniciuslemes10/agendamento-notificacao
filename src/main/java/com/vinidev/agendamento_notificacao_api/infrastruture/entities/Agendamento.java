package com.vinidev.agendamento_notificacao_api.infrastruture.entities;

import com.vinidev.agendamento_notificacao_api.infrastruture.enums.StatusNotificacao;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_agendamento")
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String emailDestinatario;

    private String telefoneDestinatario;

    private LocalDateTime dataHoraEnvio;

    private LocalDateTime dataHoraAgendamento;

    private LocalDateTime dataHoraModificacao;

    private String mensagem;

    private StatusNotificacao statusNotificacao;

    @PrePersist
    private void prePersist() {
        this.dataHoraAgendamento = LocalDateTime.now();
        this.statusNotificacao = StatusNotificacao.AGENDADO;
    }

    public Agendamento() {
    }

    public Agendamento(Long id, String emailDestinatario, String telefoneDestinatario,
                       LocalDateTime dataHoraEnvio, LocalDateTime dataHoraAgendamento,
                       LocalDateTime dataHoraModificacao, String mensagem, StatusNotificacao statusNotificacao) {
        this.id = id;
        this.emailDestinatario = emailDestinatario;
        this.telefoneDestinatario = telefoneDestinatario;
        this.dataHoraEnvio = dataHoraEnvio;
        this.dataHoraAgendamento = dataHoraAgendamento;
        this.dataHoraModificacao = dataHoraModificacao;
        this.mensagem = mensagem;
        this.statusNotificacao = statusNotificacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatario = emailDestinatario;
    }

    public String getTelefoneDestinatario() {
        return telefoneDestinatario;
    }

    public void setTelefoneDestinatario(String telefoneDestinatario) {
        this.telefoneDestinatario = telefoneDestinatario;
    }

    public LocalDateTime getDataHoraEnvio() {
        return dataHoraEnvio;
    }

    public void setDataHoraEnvio(LocalDateTime dataHoraEnvio) {
        this.dataHoraEnvio = dataHoraEnvio;
    }

    public LocalDateTime getDataHoraAgendamento() {
        return dataHoraAgendamento;
    }

    public void setDataHoraAgendamento(LocalDateTime dataHoraAgendamento) {
        this.dataHoraAgendamento = dataHoraAgendamento;
    }

    public LocalDateTime getDataHoraModificacao() {
        return dataHoraModificacao;
    }

    public void setDataHoraModificacao(LocalDateTime dataHoraModificacao) {
        this.dataHoraModificacao = dataHoraModificacao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public StatusNotificacao getStatusNotificacao() {
        return statusNotificacao;
    }

    public void setStatusNotificacao(StatusNotificacao statusNotificacao) {
        this.statusNotificacao = statusNotificacao;
    }
}

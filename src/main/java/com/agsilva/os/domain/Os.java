package com.agsilva.os.domain;

import com.agsilva.os.domain.enums.Prioridade;
import com.agsilva.os.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
@Entity
public class Os {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime dataAbertura;
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime dataFechamento;
    private Integer prioridade;
    private String observacoes;
    private Integer status;
    @ManyToOne
    @JoinColumn(name="tecnico_id")
    private Tecnico tecnico;
    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    public Os() {
        super();
        this.setDataAbertura(LocalDateTime.now());
        this.setPrioridade(Prioridade.BAIXA);
        this.setStatus(Status.ABERTO);
    }

    public Os(Integer id, Prioridade prioridade, String observacoes, Status status, Tecnico tecnico, Cliente cliente) {
        this.id = id;
        this.setDataAbertura(LocalDateTime.now());
        this.prioridade = (prioridade == null) ? 0 : prioridade.getCod();
        this.observacoes = observacoes;
        this.status = (status == null) ? 0: status.getCod();
        this.tecnico = tecnico;
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Prioridade getPrioridade() {
        return Prioridade.toEnum(this.prioridade);
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade.getCod();
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Status getStatus() {
        return Status.toEnum(this.status);
    }

    public void setStatus(Status status) {
        this.status = status.getCod();
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Os os)) return false;
        return Objects.equals(getId(), os.getId()) && Objects.equals(getDataAbertura(), os.getDataAbertura()) && Objects.equals(getDataFechamento(), os.getDataFechamento()) && getPrioridade() == os.getPrioridade() && Objects.equals(getObservacoes(), os.getObservacoes()) && getStatus() == os.getStatus() && Objects.equals(getTecnico(), os.getTecnico()) && Objects.equals(getCliente(), os.getCliente());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDataAbertura(), getDataFechamento(), getPrioridade(), getObservacoes(), getStatus(), getTecnico(), getCliente());
    }
}
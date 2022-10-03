package com.gmail.morais.caioa.scjfiapcard.dto;


import com.gmail.morais.caioa.scjfiapcard.entity.Aluno;
import com.gmail.morais.caioa.scjfiapcard.entity.Transacao;


import java.time.LocalDate;

public class TransacaoDTO {

    private Long id;
    private Long idAluno;
    private Double valor;
    private LocalDate data;

    public TransacaoDTO (Transacao transacao){
        this.id = transacao.getId();
        this.valor = transacao.getValor();
        this.data = transacao.getData();
        this.idAluno = transacao.getAluno().getIdAluno();
    }
}

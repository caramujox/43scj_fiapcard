package com.gmail.morais.caioa.scjfiapcard.dto;

import com.gmail.morais.caioa.scjfiapcard.entity.Aluno;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlunoBatchDTO {
    private String nome;
    private String cpf;
    private String rm;

    public AlunoBatchDTO (Aluno aluno) {
        this.cpf = aluno.getCpf();
        this.nome = aluno.getNome();
        this.rm = aluno.getRm();
    }

}

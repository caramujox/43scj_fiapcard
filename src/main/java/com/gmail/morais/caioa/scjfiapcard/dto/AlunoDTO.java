package com.gmail.morais.caioa.scjfiapcard.dto;

import com.gmail.morais.caioa.scjfiapcard.entity.Aluno;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDTO implements Serializable {
    private Long idAluno;
    private String nome;
    private String cpf;
    private String rm;

    public AlunoDTO (Aluno aluno) {
        this.cpf = aluno.getCpf();
        this.nome = aluno.getNome();
        this.rm = aluno.getRm();
        this.idAluno = aluno.getIdAluno();
    }
}

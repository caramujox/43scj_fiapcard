package com.gmail.morais.caioa.scjfiapcard.util.processors;

import com.gmail.morais.caioa.scjfiapcard.dto.AlunoBatchDTO;
import com.gmail.morais.caioa.scjfiapcard.entity.Aluno;
import com.gmail.morais.caioa.scjfiapcard.util.GeradorCPF;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class AlunoProcessor implements ItemProcessor<Aluno, AlunoBatchDTO> {

    @Override
    public AlunoBatchDTO process(final Aluno aluno) throws Exception {
        GeradorCPF cpfG = new GeradorCPF();
        final String nome = aluno.getNome().toUpperCase();
        final String cpf = cpfG.cpf(false);
        final String rm = aluno.getRm().replaceAll("\\.+", "").replace("-", "").trim();

        final AlunoBatchDTO transformedAluno = new AlunoBatchDTO(nome, cpf, rm);

        return transformedAluno;

    }
}

package com.gmail.morais.caioa.scjfiapcard.repository;

import com.gmail.morais.caioa.scjfiapcard.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    List<Aluno> findAllByNomeContaining(String nome);
}

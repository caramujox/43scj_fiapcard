package com.gmail.morais.caioa.scjfiapcard.service.intrefaces;

import com.gmail.morais.caioa.scjfiapcard.dto.AlunoDTO;
import com.gmail.morais.caioa.scjfiapcard.dto.CreateUpdateAlunoDTO;

import java.util.List;

public interface AlunoService {

    AlunoDTO create(CreateUpdateAlunoDTO createUpdateAlunoDTO);

    AlunoDTO findById(Long id);

    List<AlunoDTO> listAll(String nome);

    AlunoDTO update(Long id, CreateUpdateAlunoDTO createUpdateAlunoDTO);

    void delete (Long id);
}
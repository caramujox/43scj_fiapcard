package com.gmail.morais.caioa.scjfiapcard.service.intrefaces;

import com.gmail.morais.caioa.scjfiapcard.dto.CreateTransacaoDTO;
import com.gmail.morais.caioa.scjfiapcard.dto.TransacaoDTO;

import java.util.List;

public interface TransacaoService {
    TransacaoDTO create(CreateTransacaoDTO createUpdateAlunoDTO);

    TransacaoDTO findById(Long id);

    List<TransacaoDTO> listAll(Long idAluno);

    TransacaoDTO updateAlunoDTO(Long id, CreateTransacaoDTO createUpdateAlunoDTO);

    void delete (Long id);
}

package com.gmail.morais.caioa.scjfiapcard.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.morais.caioa.scjfiapcard.dto.AlunoDTO;
import com.gmail.morais.caioa.scjfiapcard.dto.CreateUpdateAlunoDTO;
import com.gmail.morais.caioa.scjfiapcard.entity.Aluno;
import com.gmail.morais.caioa.scjfiapcard.repository.AlunoRepository;
import com.gmail.morais.caioa.scjfiapcard.service.intrefaces.AlunoService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class AlunoServiceImpl implements AlunoService {

    private AlunoRepository alunoRepository;
    private ObjectMapper objectMapper;

    @Override
    @CacheEvict(cacheNames = "aluno", allEntries = true)
    public AlunoDTO create(CreateUpdateAlunoDTO createUpdateAlunoDTO) {
        Aluno aluno = objectMapper.convertValue(createUpdateAlunoDTO, Aluno.class);
        alunoRepository.save(aluno);
        return new AlunoDTO(aluno);
    }

    @Override
    @Cacheable(cacheNames = "aluno", key = "#id", unless = "#result == null")
    public AlunoDTO findById(Long id) {
        Aluno entity = alunoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return objectMapper.convertValue(entity, AlunoDTO.class);
    }

    @Override
    @Cacheable(cacheNames="aluno")
    public List<AlunoDTO> listAll(String nome) {
        List<Aluno> list;
        if (nome == null)
            list = alunoRepository.findAll();
        else
            list = alunoRepository.findAllByNomeContaining(nome);

        return list.stream().map(AlunoDTO::new).toList();
    }

    @Override
    public AlunoDTO update(Long id, CreateUpdateAlunoDTO createUpdateAlunoDTO) {
        Aluno entity = alunoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        entity.setNome(createUpdateAlunoDTO.getNome());
        entity.setCpf(createUpdateAlunoDTO.getCpf());
        entity.setRm(createUpdateAlunoDTO.getCpf());
        return null;
    }

    @Override
    public void delete(Long id) {
        Aluno entity = alunoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        alunoRepository.delete(entity);
    }
}

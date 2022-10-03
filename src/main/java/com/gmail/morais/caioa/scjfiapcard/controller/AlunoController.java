package com.gmail.morais.caioa.scjfiapcard.controller;

import com.gmail.morais.caioa.scjfiapcard.dto.AlunoDTO;
import com.gmail.morais.caioa.scjfiapcard.dto.AlunoFilterDTO;
import com.gmail.morais.caioa.scjfiapcard.dto.CreateUpdateAlunoDTO;
import com.gmail.morais.caioa.scjfiapcard.service.intrefaces.AlunoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
@AllArgsConstructor
public class AlunoController {

    private AlunoService service;

    @GetMapping
    public List<AlunoDTO> list(AlunoFilterDTO clienteFilterDTO) {
        return service.listAll(clienteFilterDTO.getNome());
    }

    @GetMapping("{id}")
    public AlunoDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlunoDTO create(@RequestBody CreateUpdateAlunoDTO createUpdateClienteDTO) {
        return service.create(createUpdateClienteDTO);
    }

    @PutMapping("{id}")
    public AlunoDTO update(@PathVariable Long id, @RequestBody CreateUpdateAlunoDTO createUpdateClienteDTO) {
        return service.update(id, createUpdateClienteDTO
        );
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id)
    {
        service.delete(id);
    }

}

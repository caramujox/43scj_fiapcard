package com.gmail.morais.caioa.scjfiapcard.controller;

import com.gmail.morais.caioa.scjfiapcard.dto.AlunoDTO;
import com.gmail.morais.caioa.scjfiapcard.dto.AlunoFilterDTO;
import com.gmail.morais.caioa.scjfiapcard.dto.CreateUpdateAlunoDTO;
import com.gmail.morais.caioa.scjfiapcard.service.intrefaces.AlunoService;
import com.gmail.morais.caioa.scjfiapcard.service.intrefaces.StorageService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.batch.runtime.JobExecution;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/alunos")
@AllArgsConstructor
public class AlunoController {

    private AlunoService service;
    private StorageService storageService;

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
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping("/batch")
    public String handleFileUpload(@RequestParam("arquivo") MultipartFile file) throws IOException {

        Resource resource = adequaResourceDoBatch(file);


        JobParameters param = new JobParametersBuilder().addString("name", "alunoJob").addString("dateTime", LocalDateTime.now().toString()).toJobParameters();
        service.handleFileUpload(resource, param);

        return "Cadastro de batch efetuado com sucesso!";
    }

    private Resource adequaResourceDoBatch(MultipartFile file){
        storageService.init();
        storageService.save(file);
        return storageService.load(file.getOriginalFilename());
    }

}

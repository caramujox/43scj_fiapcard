package com.gmail.morais.caioa.scjfiapcard.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.morais.caioa.scjfiapcard.dto.AlunoDTO;
import com.gmail.morais.caioa.scjfiapcard.dto.CreateUpdateAlunoDTO;
import com.gmail.morais.caioa.scjfiapcard.entity.Aluno;
import com.gmail.morais.caioa.scjfiapcard.repository.AlunoRepository;
import com.gmail.morais.caioa.scjfiapcard.service.intrefaces.AlunoService;
import com.gmail.morais.caioa.scjfiapcard.service.intrefaces.StorageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AlunoServiceImpl implements AlunoService {

    private AlunoRepository alunoRepository;
    private ObjectMapper objectMapper;
    private StorageService storageService;
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;

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

    @Override
    public void handleFileUpload(Resource file, JobParameters jobParameters) throws IOException {
        trataFileTxt(file);
        try {
            jobLauncher.run(job, jobParameters);
        } catch (JobInstanceAlreadyCompleteException | JobExecutionAlreadyRunningException |
                 JobParametersInvalidException | JobRestartException e) {
            throw new RuntimeException(e);
        }
    }

    private void trataFileTxt(Resource arquivoRaw) throws IOException {
        File arquivoTratado = storageService.save("lista_alunos_tratada.txt");
        FileUtils.write(arquivoTratado, "", StandardCharsets.UTF_8);
        List<String> lines = FileUtils.readLines(arquivoRaw.getFile(), StandardCharsets.UTF_8);
        lines.forEach(line -> {
            try {
                if (!(line.contains("--") || line.isEmpty() || line.isBlank())) {
                    FileUtils.writeStringToFile(arquivoTratado, line.replaceAll("\\s{2,}", ";")+"\n", StandardCharsets.UTF_8, true) ;
                }
                ;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        log.info("Tratou");
    }
}

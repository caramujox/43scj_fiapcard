package com.gmail.morais.caioa.scjfiapcard.service.intrefaces;

import com.gmail.morais.caioa.scjfiapcard.dto.AlunoDTO;
import com.gmail.morais.caioa.scjfiapcard.dto.CreateUpdateAlunoDTO;
import org.springframework.batch.core.JobParameters;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AlunoService {

    AlunoDTO create(CreateUpdateAlunoDTO createUpdateAlunoDTO);

    AlunoDTO findById(Long id);

    List<AlunoDTO> listAll(String nome);

    AlunoDTO update(Long id, CreateUpdateAlunoDTO createUpdateAlunoDTO);

    void delete (Long id);

    void handleFileUpload (Resource file, JobParameters jobParameters) throws IOException;
}
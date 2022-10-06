package com.gmail.morais.caioa.scjfiapcard.service.intrefaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
    public void init();

    public void save(MultipartFile file);
    public File save(String fileName);

    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();
}

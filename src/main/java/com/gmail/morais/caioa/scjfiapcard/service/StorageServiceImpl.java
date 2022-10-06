package com.gmail.morais.caioa.scjfiapcard.service;

import com.gmail.morais.caioa.scjfiapcard.service.intrefaces.StorageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
@Slf4j
public class StorageServiceImpl implements StorageService {

    private final Path root = Paths.get("target\\");
    private final Path batchFilePath = Paths.get(root.resolve(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyymmdd_HHmmss"))).toString());

    @Override
    public void init() {

        try {
            Files.createDirectory(root);
            Files.createDirectory(batchFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.batchFilePath.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public File save(String fileName) throws IOException {
        return new File(batchFilePath.resolve(fileName).toString());
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = batchFilePath.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }

}

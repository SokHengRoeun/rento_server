package com.sokheng.rento.rento_server.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sokheng.rento.rento_server.property.FileStorageProperties;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {

    private final Path uploadDir;

    public FileStorageService(FileStorageProperties properties) {
        this.uploadDir = Paths.get(properties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.uploadDir);
        } catch (IOException ex) {
            throw new RuntimeException("Could not create the upload directory!", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        try {
            Path targetLocation = this.uploadDir.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), targetLocation);
            return targetLocation.toString();
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + file.getOriginalFilename(), ex);
        }
    }

    public Path getFilePath(String filename) {
        return this.uploadDir.resolve(filename).normalize();
    }
}

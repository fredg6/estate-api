package com.openclassrooms.estate_api.service;

import com.openclassrooms.estate_api.exception.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileSystemStorageService {
    private final Path rootLocation;

    public FileSystemStorageService(@Value("${storage.location}") String storageLocation) {
        if(storageLocation.trim().isEmpty()){
            throw new StorageException("File upload location can not be empty.");
        }
        this.rootLocation = Paths.get(storageLocation);
        init();
    }

    public String store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            Path destinationFile = this.rootLocation.resolve(Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                throw new StorageException("Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile);
            }
            return destinationFile.toUri().toURL().toString();
        }
        catch (IOException e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new StorageException("A file with this name already exists, please rename it or choose another one.", e);
            }
            throw new StorageException("Failed to store file.", e);
        }
    }

    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
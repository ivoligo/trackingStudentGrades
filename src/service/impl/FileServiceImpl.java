package service.impl;

import exception.FileServiceException;
import service.FileService;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileServiceImpl implements FileService {

    @Override
    public Path createdFile(String filename) {
        try {
            if (Files.exists(Path.of(filename))) {
                Files.delete(Path.of(filename));
            }
            return Files.createFile(Path.of(filename));
        } catch (IOException ex) {
            throw new FileServiceException("файл не создан", ex);
        }
    }


    public OutputStream writeFile(String filename) {
        try {
            return Files.newOutputStream(Path.of(filename));
        } catch (IOException ex) {
            throw new FileServiceException("Данные не записаны в файл", ex);
        }
    }


    public Files getFile(String filename) {
        return null;
    }
}

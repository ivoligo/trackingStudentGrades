package service;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public interface FileService {

    Path createdFile(String filename);

    OutputStream writeFile(String filename);

    Files getFile(String filename);
}

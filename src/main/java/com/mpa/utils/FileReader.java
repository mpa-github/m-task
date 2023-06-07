package com.mpa.utils;

import jakarta.enterprise.context.ApplicationScoped;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@ApplicationScoped
public class FileReader {

    public String read(String pathString) throws RuntimeException, IOException {
        Path path = Paths.get(pathString).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new RuntimeException("The file '%s' does not exist!".formatted(path));
        }
        return Files.readString(path, StandardCharsets.UTF_8);
    }
}

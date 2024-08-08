package com.anixton.jobportal.util;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class FileDownloadUtil {

    private Path foundFile;

    public Optional<Resource> getFileAsResource(String downloadDir, String fileName) throws IOException {
        Path path = Paths.get(downloadDir);

        try (Stream<Path> fileStream = Files.list(path)) {
            return fileStream
                    .filter(file -> file.getFileName().toString().startsWith(fileName))
                    .findFirst()
                    .map(this::convertPathToResource);
        }
    }

    private Resource convertPathToResource(Path path) {
        try {
            return new UrlResource(path.toUri());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

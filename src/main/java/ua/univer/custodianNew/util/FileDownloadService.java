package ua.univer.custodianNew.util;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.WeakHashMap;
import java.io.File;

@Service
public class FileDownloadService {

    public static WeakHashMap<Integer, File> fileStorage = new WeakHashMap<>();
    public static WeakHashMap<String, byte[]> byteArrStorage = new WeakHashMap<>();

    private Path foundFile;

    public Resource getFileAsResource(String fileCode) throws IOException {
        Path dirPath = Paths.get("Files-Upload");
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);
        }

        Files.list(dirPath).forEach(file -> {
            if (file.getFileName().toString().startsWith(fileCode)) {
                foundFile = file;
                return;
            }
        });

        if (foundFile != null) {
            return new UrlResource(foundFile.toUri());
        }

        return null;
    }

    public Resource getFileFromStorage(Integer fileCode) throws IOException {

        File found = fileStorage.get(fileCode);

        if (found != null) {
            return new UrlResource(found.toPath().toUri());
        }

        return null;
    }

    public byte[] getByteArrFromStorage(String fileName) {

        return byteArrStorage.get(fileName);
    }

    public void deleteFromStorage (String fileName){
        byteArrStorage.remove(fileName);
    }





}

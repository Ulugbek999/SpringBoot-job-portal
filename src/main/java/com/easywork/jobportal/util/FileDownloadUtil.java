package com.easywork.jobportal.util;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;


public class FileDownloadUtil {
    

    private Path foundfile;

    public Resource getFileAsResourse(String downloadDir, String fileName) throws IOException{

        Path path = Paths.get(downloadDir);
        Files.list(path).forEach(file -> {
            if (file.getFileName().toString().startsWith(fileName)){
                foundfile = file;
            }
        });
        if(foundfile != null){
            return new UrlResource(foundfile.toUri());
        }

        return null;
    }

}

package net.javaguides.demo.BLL;

import net.javaguides.demo.exception.FileStorageException;
import net.javaguides.demo.exception.MyFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.module.Configuration;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


    @Service
    public class FileStorageService {


        private final String fileStorageLocation = System.getProperty("user.home")+"/backEndTestPhoto/" ;

        public String storeFile(MultipartFile file) {
            // Normalize file name
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            try {
                // Check if the file's name contains invalid characters
                if(fileName.contains("..")) {
                    throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
                }

                // Copy file to the target location (Replacing existing file with the same name)
                Path targetLocation = Path.of(fileStorageLocation + fileName) ;
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                return fileName;

            } catch (IOException ex) {
                throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
            }
        }

        public Resource loadFileAsResource(String fileName) {
            try {
                Path filePath = Path.of(fileStorageLocation + fileName);
                Resource resource = new UrlResource(filePath.toUri());
                if(resource.exists()) {
                    return resource;
                } else {
                    throw new MyFileNotFoundException("File not found " + fileName);
                }
            } catch (MalformedURLException ex) {
                throw new MyFileNotFoundException("File not found " + fileName, ex);
            }
        }



    }


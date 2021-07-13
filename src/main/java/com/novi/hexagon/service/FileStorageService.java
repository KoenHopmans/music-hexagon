package com.novi.hexagon.service;

import com.novi.hexagon.exceptions.FileStorageException;
import com.novi.hexagon.exceptions.MyFileNotFoundException;
import com.novi.hexagon.exceptions.RecordNotFoundException;
import com.novi.hexagon.model.User;
import com.novi.hexagon.property.FileStorageProperties;
import com.novi.hexagon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public void uploadFile(MultipartFile file) {

        try {
            Path copyLocation = Paths
                    .get(fileStorageLocation + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileStorageException("Could not store file " + file.getOriginalFilename()
                    + ". Please try again!");
        }
    }

    public void updateFile(MultipartFile file,String username) {

        System.out.println(username);
        if (!userRepository.existsById(username)) throw new RecordNotFoundException();
        User user = userRepository.findById(username).get();
        if(!(user.getPhoto() == null)){
            try {
                deleteFile(user.getPhoto());
                System.out.println("PHOTO test");
                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            Path copyLocation = Paths
                    .get(fileStorageLocation + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileStorageException("Could not store file " + file.getOriginalFilename()
                    + ". Please try again!");
        }
    }

    public void deleteFile(String filename) throws IOException {
        Path deleteLocation = Paths
                .get(fileStorageLocation + File.separator + StringUtils.cleanPath(filename));
        Files.delete(deleteLocation);
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
}

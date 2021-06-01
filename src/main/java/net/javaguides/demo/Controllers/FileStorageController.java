package net.javaguides.demo.Controllers;

import net.javaguides.demo.BLL.ApplicationUsers.ApplicationUserBLL;
import net.javaguides.demo.BLL.FileStorageService;
import net.javaguides.demo.DAL.DataBaseEntities.ApplicationUser;
import net.javaguides.demo.ClientModels.ClientModelResponse.UploadFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileStorageController {

    @Autowired
    FileStorageService fileStorageService;

    @Autowired
    ApplicationUserBLL applicationUserBLL;

    private static final Logger logger = LoggerFactory.getLogger(ApplicationUser.class);

    // normally when we upload an image to a server we should store it's path to db
    // so when we want to get the photo we just need to call a get request to the url stored in db

    // an update/create means that we should store the image in the server, here we need to make it simple
    // so a user will have just one image ( that we can name as the username plus an id )


    @PutMapping("applicationusers/{id}/uploadImage")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Long id) {

        String fileName = fileStorageService.storeFile(file);

        String filePath = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("imageFilm/"+fileName)
                .toUriString();

        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        applicationUserBLL.UpdateApplicationUserProfilePic(id,filePath);

        return new UploadFileResponse(fileName, filePath, fileDownloadUri,
                file.getContentType(), file.getSize());
    }


    @GetMapping(path="/imageFilm/{fileName}",produces= MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImageByFileName(@PathVariable (name="fileName")String fileName) throws Exception {
        File file=new File(System.getProperty("user.home")+"/backEndTestPhoto/"+ fileName) ;
        Path path= Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }


    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

    }

}

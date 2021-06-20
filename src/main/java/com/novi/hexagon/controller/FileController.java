package com.novi.hexagon.controller;

import com.novi.hexagon.model.Demo;
import com.novi.hexagon.model.Producer;
import com.novi.hexagon.model.UploadDemo;
import com.novi.hexagon.model.User;
import com.novi.hexagon.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@CrossOrigin(origins={"*"})
@RequestMapping("/api/v1/")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);


    @Autowired
    FileStorageService fileStorageService;

    @GetMapping("/file-upload")
    @ResponseStatus(HttpStatus.OK)
    public String helloUpload() {
        return "Hello upload";
    }

    @PostMapping(path="/file-upload" , consumes = {MULTIPART_FORM_DATA_VALUE})
    public String uploadFile(@RequestParam("username") String username,
                             @RequestParam("artist") Object artist,
                             @RequestParam("file") MultipartFile file,
                             RedirectAttributes redirectAttributes) {

        fileStorageService.uploadFile(file);



        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }




//    @PostMapping(path="/file-upload-database" , consumes = {MULTIPART_FORM_DATA_VALUE})
//    public String uploadFileDatabase(@RequestBody UploadDemo demo, RedirectAttributes redirectAttributes) {
//
//        MultipartFile file = demo.
//
//        fileStorageService.uploadFile(file);
//        System.out.println("FILE-NAME " + file.getOriginalFilename());
//
//            this.demoRepository.save(demo);
//
//
//
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded " + file.getOriginalFilename() + "!");
//
//        return "redirect:/";
//    }








//    @GetMapping("/file-download/example")
//    public ResponseEntity<Object> downloadFile() throws IOException
//    {
//        String filename ="C:/Users/Gebruiker/Desktop/Uploades_files/peru-test.mp3";
//        File file = new File(filename);
//        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition",
//                String.format("attachment; filename=\"%s\"",file.getName()));
//        headers.add("Cach-Control", "no-cache, no-store, must-revalidate");
//        headers.add("Expres","0");
//
//        ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers)
//                .contentLength(file.length())
//                .contentType(MediaType.parseMediaType("application/txt")).body(resource);
//
//
//        return responseEntity;
//    }





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
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}









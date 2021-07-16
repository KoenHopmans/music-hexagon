package com.novi.hexagon.controller;

import com.novi.hexagon.exceptions.BadRequestException;
import com.novi.hexagon.model.Comment;
import com.novi.hexagon.model.Demo;
import com.novi.hexagon.service.DemoService;
import com.novi.hexagon.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@RestController
@CrossOrigin(origins={"*"})
@RequestMapping("/api/v1/")
public class DemoController {

        private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    FileStorageService fileStorageService;

    @Autowired
    DemoService demoService;

    @GetMapping("demo/{filename}")
    public ResponseEntity<Object> getDemoByFilename(@PathVariable("filename") String filename){
        Demo demo = this.demoService.getDemoByFilename(filename);
        return new ResponseEntity<>(demo, HttpStatus.OK);
    }

    @PostMapping(value = "/demo")
    public ResponseEntity<Object> addDemo(@RequestParam(value = "username" ) String username,
                                          @RequestParam(value = "artist" ,required = false) String artist,
                                          @RequestParam(value = "comment" ,required = false) String comment,
                                          @RequestParam(value = "date" ,required = false) String date,
                                          @RequestParam(value = "trackName" ,required = false) String trackName,
                                          @RequestParam(value = "musicFile" ,required = false) MultipartFile musicFile,
                                          @RequestParam(value = "cover" ,required = false) MultipartFile cover) {
        try {
            fileStorageService.uploadFile(musicFile);
            if(!(cover==null)){ fileStorageService.uploadFile(cover);};
            Comment myComment = new Comment(musicFile.getOriginalFilename(),comment,date, username);
            Demo demo = new Demo();
            demo.setUsername(username);
            demo.setArtist(artist);
            if(!(comment==null)){demo.addComment(myComment);}
            demo.setTrackName(trackName);
            demo.setDemo(musicFile.getOriginalFilename());
            if(!(cover==null)){demo.setCover(cover.getOriginalFilename());}
            demoService.addDemo(demo);
            if(!(cover==null)){ System.out.println("COVER " + cover.getOriginalFilename());}
            return ResponseEntity.ok().build();
        }
        catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    @PutMapping(value = "/demo/{fileName}")
    public ResponseEntity<Object> updateDemo(@PathVariable("fileName") String fileName,
                                             @RequestParam(value = "trackName" ,required = false) String trackName,
                                             @RequestParam(value = "artist" ,required = false) String artist,
                                             @RequestParam(value = "file" ,required = false) MultipartFile file)
    {
        try {
            if(!(file==null)){fileStorageService.uploadFile(file);
                System.out.println("CHANGE FILE");}
            Demo demo = new Demo();
            demo.setArtist(artist);
            demo.setTrackName(trackName);
            if(!(file==null)){demo.setCover(file.getOriginalFilename());
                System.out.println("CHANGE FILE");}
            demoService.updateDemo(demo, fileName);
            return ResponseEntity.ok().build();
        }
        catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    @DeleteMapping(value = "demo/{filename}")
    public ResponseEntity<Object> deleteDemoByFilename(@PathVariable("filename") String filename) throws IOException {
        demoService.deleteDemo(filename);
        fileStorageService.deleteFile(filename);
        return ResponseEntity.noContent().build();
    }


    @PostMapping(value = "/{fileName}/comment")
    public ResponseEntity<Object> addComment(@PathVariable("fileName") String fileName,
                                    @RequestBody Map<String, Object> fields) {
        try {
            String comment = (String) fields.get("comment");
            String date = (String) fields.get("date");
            String messenger = (String) fields.get("messenger");
            demoService.addDemoComment(fileName, comment, date, messenger);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    @PostMapping(value = "/{fileName}/feedback")
    public ResponseEntity<Object> addFeedback(@PathVariable("fileName") String fileName,
                                             @RequestBody Map<String, Object> fields) {
        try {
            String feedback = (String) fields.get("feedback");
            String date = (String) fields.get("date");
            String messenger = (String) fields.get("messenger");
            demoService.addDemoFeedback(fileName, feedback, date, messenger);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    @DeleteMapping(value = "/{fileName}/feedback")
    public ResponseEntity<Object> deleteFeedback(@PathVariable("fileName") String fileName,
                                             @RequestBody Map<String, java.lang.Object> fields) {
        try {
            String feedback = (String) fields.get("feedback");
            demoService.deleteFeedback(fileName, feedback);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    @DeleteMapping(value = "/{fileName}/comment")
    public ResponseEntity<Object> deleteComment(@PathVariable("fileName") String fileName,
                                                 @RequestBody Map<String, java.lang.Object> fields) {
        try {
            String comment = (String) fields.get("comment");
            demoService.deleteComment(fileName, comment);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    @PutMapping(value = "/{fileName}/feedback")
    public ResponseEntity<Object> updateFeedback(@PathVariable("fileName") String fileName,
                                                 @RequestBody Map<String, java.lang.Object> fields) {
        try {
            String feedback = (String) fields.get("feedback");
            demoService.updateFeedback(fileName, feedback);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    @PutMapping(value = "/{fileName}/comment")
    public ResponseEntity<Object> updateComment(@PathVariable("fileName") String fileName,
                                                 @RequestBody Map<String, java.lang.Object> fields) {
        try {
            String comment = (String) fields.get("comment");
            demoService.updateComment(fileName, comment);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
package com.novi.hexagon.controller;

import com.novi.hexagon.exceptions.BadRequestException;
import com.novi.hexagon.model.Comment;
import com.novi.hexagon.model.Demo;
import com.novi.hexagon.model.Producer;
import com.novi.hexagon.model.User;
import com.novi.hexagon.repository.DemoRepository;
import com.novi.hexagon.service.DemoService;
import com.novi.hexagon.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins={"*"})
@RequestMapping("/api/v1/")
public class DemoController {

    @Autowired
    FileStorageService fileStorageService;

    @Autowired
    private DemoRepository demoRepository;

    @Autowired
    DemoService demoService;

    @PostMapping(value = "/demo-upload")
    public ResponseEntity<Object> addDemo(@RequestParam(value = "username" ) String username,
                                          @RequestParam(value = "artist" ,required = false) String artist,
//                                          @RequestParam(value = "feedback" ,required = false) String feedback,
                                          @RequestParam(value = "comment" ,required = false) String comment,
                                          @RequestParam(value = "trackName" ,required = false) String trackName,
                                          @RequestParam(value = "musicFile" ,required = false) MultipartFile musicFile,
                                          @RequestParam(value = "cover" ,required = false) MultipartFile cover) {
        try {
            fileStorageService.uploadFile(musicFile);
            if(!(cover==null)){ fileStorageService.uploadFile(cover);};

            Comment myComment = new Comment(musicFile.getOriginalFilename(),comment);

            Demo demo = new Demo();
            demo.setUsername(username);
            demo.setArtist(artist);
//            demo.setFeedback(feedback);
            demo.addComment(myComment);
            demo.setTrackName(trackName);
            demo.setDemo(musicFile.getOriginalFilename());
            if(!(cover==null)){demo.setCover(cover.getOriginalFilename());}

            demoService.addDemo(demo);

            System.out.println("FILE-NAME " + musicFile.getOriginalFilename());
            System.out.println("ARTIST " + artist);
//            System.out.println("FEEDBACK " + feedback);
            System.out.println("USERNAME " + username);
            if(!(cover==null)){ System.out.println("COVER " + cover.getOriginalFilename());}



            return ResponseEntity.noContent().build();
        }
        catch (Exception ex) {
            throw new BadRequestException();
        }
    }



    @GetMapping("demo/{filename}")
    public ResponseEntity<Object> getDemoByFilename(@PathVariable("filename") String filename){
        Optional<Demo> demo = this.demoService.getDemoByFilename(filename);
        return new ResponseEntity<>(demo, HttpStatus.OK);
    }

    @DeleteMapping(value = "demo/{filename}")
    public ResponseEntity<Object> deleteDemoByFilename(@PathVariable("filename") String filename) throws IOException {
        demoService.deleteDemo(filename);
        fileStorageService.deleteFile(filename);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/demo-update/{fileName}")
    @ResponseStatus(HttpStatus.OK)
    public String hello() {
        return "Hello Update";
    }

//    @PutMapping(value = "/demo-update/{fileName}")
//    public ResponseEntity<Object> updateDemo(@PathVariable("fileName") String fileName, @RequestBody Demo demo)  {
//        try {
//
//            demoService.updateDemo(demo, fileName);
//
//            return ResponseEntity.noContent().build();
//        }
//        catch (Exception ex) {
//            throw new BadRequestException();
//        }
//    }

    @PutMapping(value = "/demo-update/{fileName}")
    public ResponseEntity<Object> updateDemo(@PathVariable("fileName") String fileName,
//                                             @RequestParam(value = "username" ) String username,
                                             @RequestParam(value = "trackName" ,required = false) String trackName,
                                             @RequestParam(value = "artist" ,required = false) String artist,
                                             @RequestParam(value = "file" ,required = false) MultipartFile file)
    {
        try {
            if(!(file==null)){fileStorageService.uploadFile(file);}
            Demo demo = new Demo();
            demo.setArtist(artist);
            demo.setTrackName(trackName);
            if(!(file==null)){demo.setCover(file.getOriginalFilename());}

            demoService.updateDemo(demo, fileName);

            return ResponseEntity.noContent().build();
        }
        catch (Exception ex) {
            throw new BadRequestException();
        }
    }




    @GetMapping(value = "/{fileName}/comment")
    @ResponseStatus(HttpStatus.OK)
    public String Comment() {
        return "Hello Update";
    }


    @PostMapping(value = "/{fileName}/comment")
    public ResponseEntity<Object> addComment(@PathVariable("fileName") String fileName,
                                    @RequestBody Map<String, Object> fields) {
        try {
            String comment = (String) fields.get("comment");
            demoService.addDemoComment(fileName, comment);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }


    @PostMapping(value = "/{fileName}/feedback")
    public ResponseEntity<Object> addFeedback(@PathVariable("fileName") String fileName,
                                             @RequestBody Map<String, Object> fields) {
        try {
            String feedback = (String) fields.get("feedback");
            demoService.addDemoFeedback(fileName, feedback);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }
}
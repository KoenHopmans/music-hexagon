package com.novi.hexagon.service;

import com.novi.hexagon.exceptions.RecordNotFoundException;
import com.novi.hexagon.exceptions.UsernameNotFoundException;
import com.novi.hexagon.model.*;
import com.novi.hexagon.repository.DemoRepository;
import com.novi.hexagon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DemoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DemoRepository demoRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public void addDemo(Demo demo) {
        String username = demo.getUsername();
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.addDemo(demo);
        userRepository.save(user);
    }

    public void updateDemo(Demo newDemo, String fileName){
        if (!demoRepository.existsByDemo(fileName)) throw new UsernameNotFoundException(fileName);
        Optional<Demo> demo = demoRepository.findByDemo(fileName);
        Demo goodDemo = demo.get();
//        goodDemo.setFeedback(newDemo.getFeedback());
        goodDemo.setArtist(newDemo.getArtist());
        if(!(newDemo.getCover()==null)){goodDemo.setCover(newDemo.getCover());}
        goodDemo.setTrackName(newDemo.getTrackName());
//        goodDemo.setComment(newDemo.getComment());
        demoRepository.save(goodDemo);
    }



    public Optional<Demo> getDemoByFilename(String filename){
        if (!demoRepository.existsByDemo(filename)) throw new UsernameNotFoundException(filename);
        Optional<Demo> demo = demoRepository.findByDemo(filename);
        System.out.println(demo);
        return demo;
    }

    public void deleteDemo(String filename) throws IOException {

     if (!demoRepository.existsByDemo(filename)) throw new RecordNotFoundException();
    Demo demo = demoRepository.findByDemo(filename).get();
    String cover = demo.getCover();
        demoRepository.deleteByDemo(filename);
        fileStorageService.deleteFile(filename);
        fileStorageService.deleteFile(cover);
    }

//    public void addComment(String fileName, String comment) {
//
//        Demo demo = demoRepository.findByDemo(fileName).get();
////       demo.addComments(new Comment(username, authority));
//        demoRepository.save(demo);
//    }

    public void addDemoComment(String fileName, String comment, String date, String messenger) {
        if (!demoRepository.existsByDemo(fileName)) throw new UsernameNotFoundException(fileName);
        Demo demo = demoRepository.findByDemo(fileName).get();
        demo.addComment(new Comment(fileName, comment, date, messenger));
        demoRepository.save(demo);
    }


    public void addDemoFeedback(String fileName, String feedback, String date, String messenger) {
        if (!demoRepository.existsByDemo(fileName)) throw new UsernameNotFoundException(fileName);
        Demo demo = demoRepository.findByDemo(fileName).get();
        demo.addFeedback(new Feedback(fileName, feedback, date, messenger));
        demoRepository.save(demo);
    }

    public void deleteFeedback(String fileName, String feedback) {
        if (!demoRepository.existsByDemo(fileName)) throw new UsernameNotFoundException(fileName);
        Demo demo = demoRepository.findByDemo(fileName).get();
        Feedback feedbackToRemove =
                demo.getFeedbacks().stream().filter((a) -> a.getFeedback().equalsIgnoreCase(feedback)).findAny().get();
        demo.removeFeedback(feedbackToRemove);
        demoRepository.save(demo);
    }

    public void deleteComment(String fileName, String comment) {
        if (!demoRepository.existsByDemo(fileName)) throw new UsernameNotFoundException(fileName);
        Demo demo = demoRepository.findByDemo(fileName).get();
        Comment commentToRemove =
                demo.getComments().stream().filter((a) -> a.getComment().equalsIgnoreCase(comment)).findAny().get();
        demo.removeComment(commentToRemove);
        demoRepository.save(demo);
    }

    public void updateFeedback(String fileName, String feedback) {
        if (!demoRepository.existsByDemo(fileName)) throw new UsernameNotFoundException(fileName);
        Demo demo = demoRepository.findByDemo(fileName).get();
        Feedback feedbackToUpdate =
                demo.getFeedbacks().stream().filter((a) -> a.getFeedback().equalsIgnoreCase(feedback)).findAny().get();
        demo.removeFeedback(feedbackToUpdate);
        feedbackToUpdate.setRead(true);
        demo.addFeedback(feedbackToUpdate);
        demoRepository.save(demo);
    }

    public void updateComment(String fileName, String comment) {
        if (!demoRepository.existsByDemo(fileName)) throw new UsernameNotFoundException(fileName);
        Demo demo = demoRepository.findByDemo(fileName).get();
        Comment commentToUpdate =
                demo.getComments().stream().filter((a) -> a.getComment().equalsIgnoreCase(comment)).findAny().get();
        demo.removeComment(commentToUpdate);
        commentToUpdate.setRead(true);
        demo.addComment(commentToUpdate);
        demoRepository.save(demo);
    }

}
package com.novi.hexagon.service;

import com.novi.hexagon.exceptions.RecordNotFoundException;
import com.novi.hexagon.exceptions.UsernameNotFoundException;
import com.novi.hexagon.model.*;
import com.novi.hexagon.repository.DemoRepository;
import com.novi.hexagon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DemoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DemoRepository demoRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public DemoService(DemoRepository demoRepository, UserRepository userRepository, FileStorageService fileStorageService) {
        this.demoRepository = demoRepository;
        this.userRepository = userRepository;
        this.fileStorageService = fileStorageService;
    }


    public void addDemo(Demo demo) {
        String username = demo.getUsername();
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.addDemo(demo);
        userRepository.save(user);
    }

    public void updateDemo(Demo newDemo, String fileName){
        if (!demoRepository.existsByDemo(fileName)) throw new UsernameNotFoundException(fileName);
        Demo demo = demoRepository.findByDemo(fileName);
        Demo goodDemo = demo;
        goodDemo.setArtist(newDemo.getArtist());
        if(!(newDemo.getCover()==null)){goodDemo.setCover(newDemo.getCover());}
        goodDemo.setTrackName(newDemo.getTrackName());
        demoRepository.save(goodDemo);
    }

    public Demo getDemoByFilename(String filename){
        if (!demoRepository.existsByDemo(filename)) throw new UsernameNotFoundException(filename);
        Demo demo = demoRepository.findByDemo(filename);
        System.out.println(demo);
        return demo;
    }

    public void deleteDemo(String filename) {
        if (!demoRepository.existsByDemo(filename)) throw new RecordNotFoundException();
        Demo demo = demoRepository.findByDemo(filename);
        demoRepository.deleteByDemo(filename);
        String cover = demo.getCover();
        try {
            fileStorageService.deleteFile(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileStorageService.deleteFile(cover);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addDemoComment(String fileName, String comment, String date, String messenger) {
        if (!demoRepository.existsByDemo(fileName)) throw new UsernameNotFoundException(fileName);
        Demo demo = demoRepository.findByDemo(fileName);
        demo.addComment(new Comment(fileName, comment, date, messenger));
        demoRepository.save(demo);
    }

    public void addDemoFeedback(String fileName, String feedback, String date, String messenger) {
        if (!demoRepository.existsByDemo(fileName)) throw new UsernameNotFoundException(fileName);
        Demo demo = demoRepository.findByDemo(fileName);
        demo.addFeedback(new Feedback(fileName, feedback, date, messenger));
        demoRepository.save(demo);
    }

    public void deleteFeedback(String fileName, String feedback) {
        if (!demoRepository.existsByDemo(fileName)) throw new UsernameNotFoundException(fileName);
        Demo demo = demoRepository.findByDemo(fileName);
        Feedback feedbackToRemove =
                demo.getFeedbacks().stream().filter((a) -> a.getFeedback().equalsIgnoreCase(feedback)).findAny().get();
        demo.removeFeedback(feedbackToRemove);
        demoRepository.save(demo);
    }

    public void deleteComment(String fileName, String comment) {
        if (!demoRepository.existsByDemo(fileName)) throw new UsernameNotFoundException(fileName);
        Demo demo = demoRepository.findByDemo(fileName);
        Comment commentToRemove =
                demo.getComments().stream().filter((a) -> a.getComment().equalsIgnoreCase(comment)).findAny().get();
        demo.removeComment(commentToRemove);
        demoRepository.save(demo);
    }

    public void updateFeedback(String fileName, String feedback) {
        if (!demoRepository.existsByDemo(fileName)) throw new UsernameNotFoundException(fileName);
        Demo demo = demoRepository.findByDemo(fileName);
        Feedback feedbackToUpdate =
                demo.getFeedbacks().stream().filter((a) -> a.getFeedback().equalsIgnoreCase(feedback)).findAny().get();
        demo.removeFeedback(feedbackToUpdate);
        feedbackToUpdate.setRead(true);
        demo.addFeedback(feedbackToUpdate);
        demoRepository.save(demo);
    }

    public void updateComment(String fileName, String comment) {
        if (!demoRepository.existsByDemo(fileName)) throw new UsernameNotFoundException(fileName);
        Demo demo = demoRepository.findByDemo(fileName);
        Comment commentToUpdate =
                demo.getComments().stream().filter((a) -> a.getComment().equalsIgnoreCase(comment)).findAny().get();
        demo.removeComment(commentToUpdate);
        commentToUpdate.setRead(true);
        demo.addComment(commentToUpdate);
        demoRepository.save(demo);
    }
}
package com.novi.hexagon.service;

import com.novi.hexagon.model.Comment;
import com.novi.hexagon.model.Demo;
import com.novi.hexagon.model.Feedback;
import com.novi.hexagon.model.User;
import com.novi.hexagon.repository.DemoRepository;
import com.novi.hexagon.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DemoServiceTest {

//    @Autowired
//    private DemoService testDemoService = new DemoService();

    @InjectMocks
    private DemoService demoService;

    @Mock
    private FileStorageService fileStorageService;


    @Mock
    DemoRepository demoRepository;

    @Mock
    UserRepository userRepository;

//    @BeforeEach
//    public void setup() {
//        demoService = new DemoService(demoRepository, userRepository);
//    }

    @Test
    void addDemo() {
        Demo testDemo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");;
        User testUser = new User("testUsername");
        when(userRepository.existsById(testUser.getUsername())).thenReturn(true);
        when(userRepository.findById(testUser.getUsername())).thenReturn(java.util.Optional.of(testUser));
        demoService.addDemo(testDemo);
        verify(userRepository).save(testUser);
    }

    @Test
    void updateDemo() {
        Demo testDemo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");;
        when(demoRepository.existsByDemo(testDemo.getDemo())).thenReturn(true);
        when(demoRepository.findByDemo(testDemo.getDemo())).thenReturn(testDemo);
        demoService.updateDemo(testDemo, testDemo.getDemo());
        verify(demoRepository).save(testDemo);
    }

    @Test
    void getDemoByFilename() {
        Demo testDemo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");;
        when(demoRepository.existsByDemo(testDemo.getDemo())).thenReturn(true);
        demoService.getDemoByFilename(testDemo.getDemo());
        verify(demoRepository).findByDemo(testDemo.getDemo());
    }

    @Test
    void deleteDemo() throws IOException {
        Demo testDemo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");;
        when(demoRepository.existsByDemo(testDemo.getDemo())).thenReturn(true);
        when(demoRepository.findByDemo(testDemo.getDemo())).thenReturn(testDemo);
        demoService.deleteDemo(testDemo.getDemo());
//        when(fileStorageService.deleteFile(testDemo.getDemo())).thenReturn(true);;
//        demoService.deleteDemo(testDemo.getDemo());
//        when(fileStorageService.deleteFile(testDemo.getDemo())).thenReturn(true);
        verify(demoRepository).deleteByDemo(testDemo.getDemo());
        verify(fileStorageService).deleteFile(testDemo.getCover());
        verify(fileStorageService).deleteFile(testDemo.getDemo());
    }

    @Test
    void addDemoComment() {
        Demo testDemo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");;
        when(demoRepository.existsByDemo(testDemo.getDemo())).thenReturn(true);
        when(demoRepository.findByDemo(testDemo.getDemo())).thenReturn(testDemo);
        demoService.addDemoComment("testDemo", "testComment", "01/01/2021","testMessenger");
        verify(demoRepository).save(testDemo);
    }

    @Test
    void addDemoFeedback() {
        Demo testDemo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");;
        when(demoRepository.existsByDemo(testDemo.getDemo())).thenReturn(true);
        when(demoRepository.findByDemo(testDemo.getDemo())).thenReturn(testDemo);
        demoService.addDemoFeedback("testDemo", "testFeedback", "01/01/2021","testMessenger");
        verify(demoRepository).save(testDemo);
    }

    @Test
    void deleteFeedback() {
        Demo testDemo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");;
        Feedback testFeedback = new Feedback("testDemo", "testFeedback", "01/01/2021","testMessenger");
        testDemo.addFeedback(testFeedback);
        when(demoRepository.existsByDemo(testDemo.getDemo())).thenReturn(true);
        when(demoRepository.findByDemo(testDemo.getDemo())).thenReturn(testDemo);
        demoService.deleteFeedback(testDemo.getDemo(),"testFeedback");
        verify(demoRepository).save(testDemo);
    }

    @Test
    void deleteComment() {
        Demo testDemo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");;
        Comment testComment = new Comment("testDemo", "testComment", "01/01/2021","testMessenger");
        testDemo.addComment(testComment);
        when(demoRepository.existsByDemo(testDemo.getDemo())).thenReturn(true);
        when(demoRepository.findByDemo(testDemo.getDemo())).thenReturn(testDemo);
        demoService.deleteComment(testDemo.getDemo(),"testComment");
        verify(demoRepository).save(testDemo);
    }

    @Test
    void updateFeedback() {
        Demo testDemo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");;
        Feedback testFeedback = new Feedback("testDemo", "testFeedback", "01/01/2021","testMessenger");
        testDemo.addFeedback(testFeedback);
        when(demoRepository.existsByDemo(testDemo.getDemo())).thenReturn(true);
        when(demoRepository.findByDemo(testDemo.getDemo())).thenReturn(testDemo);
        demoService.updateFeedback(testDemo.getDemo(),"testFeedback");
        verify(demoRepository).save(testDemo);

    }

    @Test
    void updateComment() {
        Demo testDemo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");;
        Comment testComment = new Comment("testDemo", "testComment", "01/01/2021","testMessenger");
        testDemo.addComment(testComment);
        when(demoRepository.existsByDemo(testDemo.getDemo())).thenReturn(true);
        when(demoRepository.findByDemo(testDemo.getDemo())).thenReturn(testDemo);
        demoService.updateComment(testDemo.getDemo(),"testComment");
        verify(demoRepository).save(testDemo);
    }
}
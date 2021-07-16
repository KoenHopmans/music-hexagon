package com.novi.hexagon.service;

import com.novi.hexagon.exceptions.RecordNotFoundException;
import com.novi.hexagon.exceptions.UsernameNotFoundException;
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

    @InjectMocks
    private DemoServiceImpl demoService;

    @Mock
    private FileStorageService fileStorageService;

    @Mock
    DemoRepository demoRepository;

    @Mock
    UserRepository userRepository;

    @Test
    void testAddDemo() {
        Demo testDemo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");;
        User testUser = new User("testUsername");
        when(userRepository.existsById(testUser.getUsername())).thenReturn(true);
        when(userRepository.findById(testUser.getUsername())).thenReturn(java.util.Optional.of(testUser));
        demoService.addDemo(testDemo);
        verify(userRepository).save(testUser);
    }

    @Test
    void testAddDemo_NotFoundException() {
        Demo testDemo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");;
        User testUser = new User("testUsername");
        when(userRepository.existsById(testUser.getUsername())).thenReturn(false);
        assertThrows(UsernameNotFoundException.class,()->{demoService.addDemo(testDemo);;});
    }

    @Test
    void testUpdateDemo() {
        Demo testDemo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");;
        when(demoRepository.existsByDemo(testDemo.getDemo())).thenReturn(true);
        when(demoRepository.findByDemo(testDemo.getDemo())).thenReturn(testDemo);
        demoService.updateDemo(testDemo, testDemo.getDemo());
        verify(demoRepository).save(testDemo);
    }

    @Test
    void testGetDemoByFilename() {
        Demo testDemo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");;
        when(demoRepository.existsByDemo(testDemo.getDemo())).thenReturn(true);
        demoService.getDemoByFilename(testDemo.getDemo());
        verify(demoRepository).findByDemo(testDemo.getDemo());
    }

    @Test
    void testGetDemoByFilename_NotFoundException() {
        Demo testDemo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");;
        when(demoRepository.existsByDemo(testDemo.getDemo())).thenReturn(false);
        assertThrows(UsernameNotFoundException.class,()->{demoService.getDemoByFilename(testDemo.getDemo());});
    }

    @Test
    void testDeleteDemo() throws IOException {
        Demo testDemo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");;
        when(demoRepository.existsByDemo(testDemo.getDemo())).thenReturn(true);
        when(demoRepository.findByDemo(testDemo.getDemo())).thenReturn(testDemo);
        demoService.deleteDemo(testDemo.getDemo());
        verify(demoRepository).deleteByDemo(testDemo.getDemo());
        verify(fileStorageService).deleteFile(testDemo.getCover());
        verify(fileStorageService).deleteFile(testDemo.getDemo());
    }

    @Test
    void testDeleteDemo_NotFoundException() {
        Demo testDemo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");;
        when(demoRepository.existsByDemo(testDemo.getDemo())).thenReturn(false);
        assertThrows(RecordNotFoundException.class,()->{demoService.deleteDemo(testDemo.getDemo());});
    }

    @Test
    void testAddDemoComment() {
        Demo testDemo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");;
        when(demoRepository.existsByDemo(testDemo.getDemo())).thenReturn(true);
        when(demoRepository.findByDemo(testDemo.getDemo())).thenReturn(testDemo);
        demoService.addDemoComment("testDemo", "testComment", "01/01/2021","testMessenger");
        verify(demoRepository).save(testDemo);
    }

    @Test
    void testAddDemoComment_NotFoundException() {
        Demo testDemo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");;
        String testComment = "testComment";
        String testDate = "testDate";
        String testMessenger = "testMessenger";
        when(demoRepository.existsByDemo(testDemo.getDemo())).thenReturn(false);
        assertThrows(RecordNotFoundException.class,
                ()->{demoService.addDemoComment(testDemo.getDemo(),testComment,testDate,testMessenger);});
    }

    @Test
    void testAddDemoFeedback() {
        Demo testDemo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");;
        when(demoRepository.existsByDemo(testDemo.getDemo())).thenReturn(true);
        when(demoRepository.findByDemo(testDemo.getDemo())).thenReturn(testDemo);
        demoService.addDemoFeedback("testDemo", "testFeedback", "01/01/2021","testMessenger");
        verify(demoRepository).save(testDemo);
    }

    @Test
    void testAddDemoFeedback_NotFoundException() {
        Demo testDemo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");;
        String testFeedback = "testFeedback";
        String testDate = "testDate";
        String testMessenger = "testMessenger";
        when(demoRepository.existsByDemo(testDemo.getDemo())).thenReturn(false);
        assertThrows(RecordNotFoundException.class,
                ()->{demoService.addDemoFeedback(testDemo.getDemo(),testFeedback,testDate,testMessenger);});
    }


    @Test
    void testDeleteComment() {
        Demo testDemo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");;
        Comment testComment = new Comment("testDemo", "testComment", "01/01/2021","testMessenger");
        testDemo.addComment(testComment);
        when(demoRepository.existsByDemo(testDemo.getDemo())).thenReturn(true);
        when(demoRepository.findByDemo(testDemo.getDemo())).thenReturn(testDemo);
        demoService.deleteComment(testDemo.getDemo(),testComment.getComment());
        verify(demoRepository).save(testDemo);
    }

    @Test
    void testDeleteComment_NotFoundException() {
        Demo testDemo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");;
        Comment testComment = new Comment("testDemo", "testComment", "01/01/2021","testMessenger");
        when(demoRepository.existsByDemo(testDemo.getDemo())).thenReturn(false);
        assertThrows(RecordNotFoundException.class,
                ()->{demoService.deleteComment(testDemo.getDemo(),testComment.getComment());});
    }

    @Test
    void testDeleteFeedback() {
        Demo testDemo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");;
        Feedback testFeedback = new Feedback("testDemo", "testFeedback", "01/01/2021","testMessenger");
        testDemo.addFeedback(testFeedback);
        when(demoRepository.existsByDemo(testDemo.getDemo())).thenReturn(true);
        when(demoRepository.findByDemo(testDemo.getDemo())).thenReturn(testDemo);
        demoService.deleteFeedback(testDemo.getDemo(),"testFeedback");
        verify(demoRepository).save(testDemo);
    }

    @Test
    void testDeleteFeedback_NotFoundException() {
        Demo testDemo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");;
        Feedback testFeedback = new Feedback("testDemo", "testFeedback", "01/01/2021","testMessenger");
        when(demoRepository.existsByDemo(testDemo.getDemo())).thenReturn(false);
        assertThrows(RecordNotFoundException.class,
                ()->{demoService.deleteFeedback(testDemo.getDemo(),testFeedback.getFeedback());});
    }

    @Test
    void testUpdateComment() {
        Demo testDemo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");;
        Comment testComment = new Comment("testDemo", "testComment", "01/01/2021","testMessenger");
        testDemo.addComment(testComment);
        when(demoRepository.existsByDemo(testDemo.getDemo())).thenReturn(true);
        when(demoRepository.findByDemo(testDemo.getDemo())).thenReturn(testDemo);
        demoService.updateComment(testDemo.getDemo(),"testComment");
        verify(demoRepository).save(testDemo);
    }

    @Test
    void testUpdateComment_NotFoundException() {
        Demo testDemo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");;
        Comment testComment = new Comment("testDemo", "testComment", "01/01/2021","testMessenger");
        testDemo.addComment(testComment);
        when(demoRepository.existsByDemo(testDemo.getDemo())).thenReturn(false);
        assertThrows(RecordNotFoundException.class,
                ()->{demoService.updateComment(testDemo.getDemo(),"testComment");});
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
    void updateFeedback_NotFoundException() {
        Demo testDemo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");;
        Feedback testFeedback = new Feedback("testDemo", "testComment", "01/01/2021","testMessenger");
        testDemo.addFeedback(testFeedback);
        when(demoRepository.existsByDemo(testDemo.getDemo())).thenReturn(false);
        assertThrows(RecordNotFoundException.class,
                ()->{demoService.updateFeedback(testDemo.getDemo(),"testFeedback");});
    }
}
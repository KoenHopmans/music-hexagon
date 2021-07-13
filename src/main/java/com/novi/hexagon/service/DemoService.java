package com.novi.hexagon.service;

import com.novi.hexagon.model.Demo;

public interface DemoService {
    public abstract void addDemo(Demo demo);
    public abstract void updateDemo(Demo newDemo, String fileName);
    public abstract Demo getDemoByFilename(String filename);
    public abstract void deleteDemo(String filename);
    public abstract void addDemoComment(String fileName, String comment, String date, String messenger);
    public abstract void addDemoFeedback(String fileName, String feedback, String date, String messenger);
    public abstract void deleteFeedback(String fileName, String feedback);
    public abstract void deleteComment(String fileName, String comment);
    public abstract void updateFeedback(String fileName, String feedback);
    public abstract void updateComment(String fileName, String comment);
}

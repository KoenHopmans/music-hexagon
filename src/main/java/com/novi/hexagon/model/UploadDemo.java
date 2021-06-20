package com.novi.hexagon.model;

import org.springframework.web.multipart.MultipartFile;

public class UploadDemo {

    public UploadDemo(MultipartFile file, String fileName) {
        this.file = file;
        this.fileName = fileName;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private MultipartFile file;
    private String fileName;
}

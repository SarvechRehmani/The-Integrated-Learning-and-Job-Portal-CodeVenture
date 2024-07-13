package com.codeventure.services;

import com.codeventure.entities.User;
import com.codeventure.entities.job.Job;
import com.codeventure.entities.job.JobsApplications;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {

    String uploadImage(String path, MultipartFile file, User user) throws IOException;

    InputStream getImage(String path, String fileName) throws FileNotFoundException;

    boolean deleteImage(String fullPath) throws Exception;

    String uploadResume(String path, MultipartFile file) throws IOException;

    InputStream getResume(String path, String fileName) throws FileNotFoundException;

    public boolean deleteResume(String fullPath) throws Exception;

}

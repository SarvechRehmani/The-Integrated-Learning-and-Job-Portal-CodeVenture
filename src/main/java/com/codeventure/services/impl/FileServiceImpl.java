package com.codeventure.services.impl;

import com.codeventure.entities.User;
import com.codeventure.entities.job.Job;
import com.codeventure.entities.job.JobsApplications;
import com.codeventure.services.FileService;
import com.codeventure.services.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JobServiceImpl jobService;


    @Autowired
    private JobApplicationServiceImpl jobApplicationService;
    @Override
    public String uploadImage(String path, MultipartFile file, User user) throws IOException {
//        Get File name
        String fileName = file.getOriginalFilename();
        String randomId = UUID.randomUUID().toString();
        String randomFileName = randomId.concat(fileName.substring(fileName.lastIndexOf(".")));
//        Make Full Path
        String filePath = path+File.separator+randomFileName;
//        Create Folder If Not Exist
        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }
//         Copy File
        Files.copy(file.getInputStream(), Paths.get(filePath));

        user.setProfile(randomFileName);
        this.userService.updateUser(user);
        return randomFileName;
    }

    @Override
    public InputStream getImage(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream is = new FileInputStream(fullPath);
        return is;
    }

    @Override
    public boolean deleteImage(String fullPath) throws Exception {

        File file = new File(fullPath);
        try {
            return file.delete();
        }catch(Exception e){
            throw new Exception("File Not Found");
        }
    }

//    Uploading resume
    @Override
    public String uploadResume(String path, MultipartFile file) throws IOException {
//        Get file name
        String fileName = file.getOriginalFilename();
//        Make random File name for uniqueness
        String randomName = String.valueOf(System.currentTimeMillis());
        String randomFileName = randomName.concat(fileName.substring(fileName.lastIndexOf(".")));
//        Make full path
        String fullPath = path+File.separator+randomFileName;

        File file1 = new File(path);
//        Checking if folder is exists if not then Make folder.
        if(!file1.exists()){
            file1.mkdir();
        }
//        Copying File
        Files.copy(file.getInputStream(), Paths.get(fullPath));

        return randomFileName;
    }

//    Get Resume
    @Override
    public InputStream getResume(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream is = new FileInputStream(fullPath);
        return is;
    }

    @Override
    public boolean deleteResume(String fullPath) throws Exception {

        File file = new File(fullPath);
        try {
            return file.delete();
        }catch(Exception e){
            throw new Exception("File Not Found");
        }
    }
}

package com.codeventure.controller;

import com.codeventure.entities.User;
import com.codeventure.entities.job.Job;
import com.codeventure.entities.job.JobsApplications;
import com.codeventure.payload.FileResponse;
import com.codeventure.services.impl.FileServiceImpl;
import com.codeventure.services.impl.JobApplicationServiceImpl;
import com.codeventure.services.impl.UserDetailsServiceImpl;
import com.codeventure.services.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@CrossOrigin("*")
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileServiceImpl fileService;

    @Autowired
    private JobApplicationServiceImpl jobApplicationService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Value("${project.image}")
    private String path;

    @Value("${project.resume}")
    private String resumePath;

//    Uploading Image (Profile picture)
    @PostMapping("/upload")
    public ResponseEntity<FileResponse> uploadImage(@RequestParam("image") MultipartFile image, Principal principal) throws Exception {
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
       String oldImage = user.getProfile();
        if(image == null){
            throw new Exception("Image is null. Please select Image..");
        }else{
            try {
                String fileName = this.fileService.uploadImage(path, image, user);
                if(!oldImage.equalsIgnoreCase("Default.png")){
                    this.fileService.deleteImage(path + File.separator + oldImage);
                }
                return new ResponseEntity<>(new FileResponse(fileName, "Image is Successfully Uploaded.."), HttpStatus.OK);

            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

//    Getting image (Profile picture)
    @PermitAll
    @GetMapping(value = "/profile/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        InputStream image = this.fileService.getImage(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(image, response.getOutputStream());
    }



    //    Uploading Resume
    @PostMapping("/resume/upload/{jobId}")
    public ResponseEntity<FileResponse> uploadResume(@RequestParam("resume") MultipartFile resume, @RequestParam("jobApplications") String jobApplications, @PathVariable("jobId") long jobId, Principal principal) throws Exception {
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));

        JobsApplications jobsApplications1 = new ObjectMapper().readValue(jobApplications, JobsApplications.class);

        Job job = new Job();
        job.setjId(jobId);

        if(resume == null){
            throw new Exception("File is null. Please select file..");
        }else{
            try {
                String fileName = this.fileService.uploadResume(resumePath, resume);
                fileName.length();


                jobsApplications1.setResume(fileName);
                jobsApplications1.setJob(job);
                jobsApplications1.setUser(user);

                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                jobsApplications1.setDate(dateFormat.format(date));

                this.jobApplicationService.saveJobsApplications(jobsApplications1);
                return new ResponseEntity<>(new FileResponse(fileName, "Your Application is Successfully submitted."), HttpStatus.OK);

            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseEntity<>(new FileResponse(null, "Error in Uploading File..."), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }


    //    Getting Resume
    @PermitAll
    @GetMapping(value = "/resume/{resumeName}",produces = MediaType.APPLICATION_PDF_VALUE)
    public void getResume(@PathVariable("resumeName") String resumeName, HttpServletResponse response) throws IOException {
        InputStream resume = this.fileService.getResume(resumePath, resumeName);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        StreamUtils.copy(resume, response.getOutputStream());
    }
}

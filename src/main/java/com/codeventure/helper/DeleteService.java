package com.codeventure.helper;

import com.codeventure.entities.User;
import com.codeventure.entities.UserRole;
import com.codeventure.entities.course.*;
import com.codeventure.entities.job.Job;
import com.codeventure.entities.job.JobsApplications;
import com.codeventure.entities.result.AssignmentResult;
import com.codeventure.entities.result.LabTaskResult;
import com.codeventure.entities.result.QuizResult;
import com.codeventure.repo.UserRepository;
import com.codeventure.repo.course.*;
import com.codeventure.repo.job.JobApplicationsRepository;
import com.codeventure.repo.result.AssignmentResultRepository;
import com.codeventure.repo.result.LabTaskResultRepository;
import com.codeventure.repo.result.QuizResultRepository;
import com.codeventure.services.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeleteService {

    @Autowired
    private UserRepository userRepository;

    //    COURSE REPOSITORIES
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private LectureRepository lectureRepository;

    //    TASK REPOSITORIES
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private LabTaskRepository labTaskRepository;
    @Autowired
    private QuizRepository quizRepository;

    //    RESULT REPOSITORIES
    @Autowired
    private AssignmentResultRepository assignmentResultRepository;
    @Autowired
    private LabTaskResultRepository labTaskResultRepository;
    @Autowired
    private QuizResultRepository quizResultRepository;

    //    JOB REPOSITORY
    @Autowired
    private JobRepository jobRepository;

    //    JOB APPLICATION REPOSITORY
    @Autowired
    private JobApplicationsRepository jobApplicationsRepository;

    @Value("${project.resume}")
    private String resumePath;

    public void deleteUser(long uId) {
        User user = this.userRepository.findById(uId).orElse(null);
        if (user != null) {
            for (UserRole userRole : user.getUsersRoles()) {
//                FOR MENTOR ROLE
                if (userRole.getRole().getRoleId() == 24) {
                    List<Course> courses = new ArrayList<>(user.getCourses());
                    for (Course course : courses) {
                        this.deleteCourse(course.getcId());
                    }
                    user.getCourses().clear();

                }
//                FOR NORMAL ROLE
                else if (userRole.getRole().getRoleId() == 23) {
                    List<AssignmentResult> assignmentResults = new ArrayList<>(user.getAssignmentResults());
                    for (AssignmentResult assignmentResult : assignmentResults) {
                        this.deleteAssignmentResult(assignmentResult.getAssignmentResultId());
                    }
                    List<LabTaskResult> labTaskResults = new ArrayList<>(user.getLabTaskResults());
                    for (LabTaskResult labTaskResult : labTaskResults) {
                        this.deleteLabTaskResult(labTaskResult.getLabTaskResultId());
                    }
                    List<QuizResult> quizResults = new ArrayList<>(user.getQuizResult());
                    for (QuizResult quizResult : quizResults) {
                        this.deleteQuizResult(quizResult.getQuizResultId());
                    }
//                    DELETING JOB APPLICATION WHICH USER APPLIED
                    List<JobsApplications> jobsApplications = new ArrayList<>(user.getJobsApplications());
                    for (JobsApplications jobsApplication : jobsApplications) {
                        this.deleteJobApplications(jobsApplication.getJobAId());
                    }

                    user.getAssignmentResults().clear();
                    user.getLabTaskResults().clear();
                    user.getQuizResult().clear();

                    user.getJobsApplications().clear();
                } else if (userRole.getRole().getRoleId() == 25) {
                    List<Job> jobs = new ArrayList<>(user.getJobs());
                    for (Job job : jobs) {
                        this.deleteJob(job.getjId());
                    }
                    user.getJobs().clear();
                }
            }
            this.userRepository.delete(user);
        }
    }

    //    COURSE
    public void deleteCourse(long cId) {
        Course course = this.courseRepository.findById(cId).orElse(null);
        if (course != null) {
            List<Lecture> lectures = new ArrayList<>(course.getLectures());
            for (Lecture lecture : lectures) {
                this.deleteLecture(lecture.getlId());
            }
            course.getLectures().clear();
            User user = course.getUser();
            user.getCourses().remove(course);
            this.userRepository.save(user);
        }
    }

    public void deleteLecture(long lId) {
        Lecture lecture = this.lectureRepository.findById(lId).orElse(null);
        if (lecture != null) {
            if (lecture.getAssignment() != null) {
                this.deleteAssignment(lecture.getAssignment().getaId());
            }
            if (lecture.getLabTask() != null) {
                this.deleteLabTask(lecture.getLabTask().getLabId());
            }
            if (lecture.getQuiz() != null) {
                this.deleteQuiz(lecture.getQuiz().getqId());
            }
            Course course = lecture.getCourse();
            course.getLectures().remove(lecture);
            this.courseRepository.save(course);
        }
    }

    //    TASKS
    public void deleteAssignment(long aId) {
        Assignment assignment = this.assignmentRepository.findById(aId).orElse(null);
        if (assignment != null) {
            List<AssignmentResult> assignmentResultsCopy = new ArrayList<>(assignment.getAssignmentResults());
            for (AssignmentResult assignmentResult : assignmentResultsCopy) {
                this.deleteAssignmentResult(assignmentResult.getAssignmentResultId());
            }
            assignment.getAssignmentResults().clear();
            Lecture lecture = assignment.getLecture();
            lecture.setAssignment(null);
            this.lectureRepository.save(lecture);
        }
    }

    public void deleteLabTask(long lId) {
        LabTask labTask = this.labTaskRepository.findById(lId).orElse(null);
        if (labTask != null) {
            List<LabTaskResult> labTaskResultsCopy = new ArrayList<>(labTask.getLabTaskResults());
            for (LabTaskResult labTaskResult : labTaskResultsCopy) {
                this.deleteLabTaskResult(labTaskResult.getLabTaskResultId());
            }
            labTask.getLabTaskResults().clear();
            Lecture lecture = labTask.getLecture();
            lecture.setLabTask(null);
            this.lectureRepository.save(lecture);
        }
    }

    public void deleteQuiz(long qId) {
        Quiz quiz = this.quizRepository.findById(qId).orElse(null);
        if (quiz != null) {
            List<QuizResult> quizResultsCopy = new ArrayList<>(quiz.getQuizResult());
            for (QuizResult quizResult : quizResultsCopy) {
                this.deleteQuizResult(quizResult.getQuizResultId());
            }
            quiz.getQuizResult().clear();
            Lecture lecture = quiz.getLecture();
            lecture.setQuiz(null);
            this.lectureRepository.save(lecture);
        }
    }

    //    RESULT
    public void deleteAssignmentResult(long arId) {
        AssignmentResult assignmentResult = this.assignmentResultRepository.findById(arId).orElse(null);
        if (assignmentResult != null) {
            Assignment assignment = assignmentResult.getAssignment();
            assignment.getAssignmentResults().remove(assignmentResult);
            User user = assignmentResult.getUser();
            user.getAssignmentResults().remove(assignmentResult);
            this.assignmentRepository.save(assignment);
            this.userRepository.save(user);
        }
    }

    public void deleteLabTaskResult(long lrId) {
        LabTaskResult labTaskResult = this.labTaskResultRepository.findById(lrId).orElse(null);
        if (labTaskResult != null) {
            LabTask labTask = labTaskResult.getLabTask();
            labTask.getLabTaskResults().remove(labTaskResult);
            User user = labTaskResult.getUser();
            user.getLabTaskResults().remove(labTaskResult);
            this.labTaskRepository.save(labTask);
            this.userRepository.save(user);
        }
    }

    public void deleteQuizResult(long qId) {
        QuizResult quizResult = this.quizResultRepository.findById(qId).orElse(null);
        if (quizResult != null) {
            Quiz quiz = quizResult.getQuiz();
            quiz.getQuizResult().remove(quizResult);
            User user = quizResult.getUser();
            user.getQuizResult().remove(quizResult);
            this.quizRepository.save(quiz);
            this.userRepository.save(user);
        }
    }

    //    JOB

    public void deleteJob(long jId) {
        Job job = this.jobRepository.findById(jId).orElse(null);
        if (job != null) {
            List<JobsApplications> jobsApplications = new ArrayList<>(job.getJobsApplications());
            for (JobsApplications jobsApplication : jobsApplications) {
                this.deleteJobApplications(jobsApplication.getJobAId());
            }
            job.getJobsApplications().clear();
            User user = job.getUser();
            user.getJobs().remove(job);
            this.userRepository.save(user);
        }
    }

    public void deleteJobApplications(long jaId) {
        JobsApplications jobsApplications = this.jobApplicationsRepository.findById(jaId).orElse(null);
        if (jobsApplications != null) {
            Job job = jobsApplications.getJob();
            job.getJobsApplications().remove(jobsApplications);
            User user = jobsApplications.getUser();
            user.getJobsApplications().remove(jobsApplications);
            this.deleteResume(resumePath+jobsApplications.getResume());
            this.jobRepository.save(job);
            this.userRepository.save(user);
        }
    }

    public void deleteResume(String fullPath) {
        File file = new File(fullPath);
        file.delete();
    }
}

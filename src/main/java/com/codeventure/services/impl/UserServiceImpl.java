package com.codeventure.services.impl;

import com.codeventure.entities.User;
import com.codeventure.entities.UserRole;
import com.codeventure.entities.course.*;
import com.codeventure.entities.result.AssignmentResult;
import com.codeventure.entities.result.LabTaskResult;
import com.codeventure.entities.result.QuizResult;
import com.codeventure.helper.DeleteService;
import com.codeventure.helper.UserFoundException;
import com.codeventure.repo.RoleRepository;
import com.codeventure.repo.UserRepository;
import com.codeventure.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DeleteService deleteService;

    //    Creating User
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws UserFoundException {

        User local = this.userRepository.findByUsername(user.getUsername());
        if (local != null) {
            System.out.println("Username is already exist.");
            throw new UserFoundException("User with this username is already Registered..! try another username.");
        } else {
            for (UserRole ur : userRoles) {
                this.roleRepository.save(ur.getRole());
            }
            user.getUsersRoles().addAll(userRoles);
            local = this.userRepository.save(user);
        }
        return local;
    }

    @Override
    public User getUserById(long id) {
        return this.userRepository.findById(id).get();
    }

    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public User updateUser(User user) {
        User user1 = this.getUserById(user.getId());
        user.setUsersRoles(user1.getUsersRoles());

        user.setCourses(user1.getCourses());
        user.setQuizResult(user1.getQuizResult());
        user.setAssignmentResults(user1.getAssignmentResults());
        user.setLabTaskResults(user1.getLabTaskResults());

        user.setJobs(user1.getJobs());
        user.setJobsApplications(user1.getJobsApplications());
        return this.userRepository.save(user);
    }

    @Override
    public void deleteUser(long userId){
       this.deleteService.deleteUser(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

}

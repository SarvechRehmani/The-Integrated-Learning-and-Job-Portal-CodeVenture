package com.codeventure.services;

import com.codeventure.entities.User;
import com.codeventure.entities.UserRole;

import java.util.List;
import java.util.Set;

public interface UserService {

//    Creating User
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    //    Get User by Id
    public User getUserById(long id);

//    Get User by username
    public User getUser(String username);

//    Update user
    public User updateUser(User user);

//    Delete user by id
    public void deleteUser(long userId);

    public List<User> getAllUsers();
}

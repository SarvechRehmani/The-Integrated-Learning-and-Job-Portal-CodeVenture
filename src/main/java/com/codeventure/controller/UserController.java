package com.codeventure.controller;

import com.codeventure.entities.Role;
import com.codeventure.entities.User;
import com.codeventure.entities.UserRole;
import com.codeventure.helper.PasswordNotMatch;
import com.codeventure.helper.UserFoundException;
import com.codeventure.payload.ChangePassword;
import com.codeventure.services.RoleService;
import com.codeventure.services.UserService;
import com.codeventure.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private RoleService roleServiceImpl;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/test")
    public String test(){
        return "Welcome to CodeVenture Backend API";
    }

//    Registration of User
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {

//        Encode password using BCryptPasswordEncoder
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        user.setDate(dateFormat.format(date));

        Role role = new Role();
        if (user.getCheckRole().equalsIgnoreCase("NORMAL")) {
//            Add NORMAL Role
            role.setRoleId(23);
            role.setRoleName("NORMAL");

        } else if (user.getCheckRole().equalsIgnoreCase("MENTOR")) {
//            Add MENTOR Role
            role.setRoleId(24);
            role.setRoleName("MENTOR");
        }
        else if (user.getCheckRole().equalsIgnoreCase("COMPANY")) {
//          Add company Role
          role.setRoleId(25);
          role.setRoleName("COMPANY");
      }

        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);

        Set<UserRole> userRoleSet = new HashSet<>();
        userRoleSet.add(userRole);

        return this.userService.createUser(user, userRoleSet);
    }

    //    get User by id
    @GetMapping("/byId/{id}")
    public User getUserById(@PathVariable("id") long id) {
        return this.userService.getUserById(id);
    }


    //    get User by username
    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username) {
        return this.userService.getUser(username);
    }

//    Update User
    @PutMapping("/")
    public User updateUser(@RequestBody User user){
        return this.userService.updateUser(user);
    }

    //    delete user by Id
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") long userId){
        this.userService.deleteUser(userId);
    }


//    Mentors Operations:->

    //    Get All Mentors:
    @GetMapping("/mentors")
    public Set<User> getAllMentors() {
//        Getting Role using RoleId of Mentor
        Role role = this.roleServiceImpl.getRole(24);
        Set<User> users = new HashSet<>();
        for (UserRole userRole : role.getUsersRoles()) {
            users.add(userRole.getUser());
        }
        return users;
    }


    //    Get All Companies:
    @GetMapping("/companies")
    public List<User> getAllCompanies() {
//        Getting Role using RoleId of Mentor
        Role role = this.roleServiceImpl.getRole(25);
        List<User> users = new ArrayList<>();
        for (UserRole userRole : role.getUsersRoles()) {
            users.add(userRole.getUser());
        }
        return users;
    }
    //    Get All Normal Users:
    @GetMapping("/users")
    public List<User> getAllNormalUsers() {
//        Getting Role using RoleId of Normal Users
        Role role = this.roleServiceImpl.getRole(23);
        List<User> users = new ArrayList<>();
        for (UserRole userRole : role.getUsersRoles()) {
            users.add(userRole.getUser());
        }
        return users;
    }



    //    Change Password /user/update/password
    @PutMapping("/update/password")
    public User changePassword(@RequestBody  ChangePassword changePassword, Principal principal) throws PasswordNotMatch {
        User user = (User) (this.userDetailsService.loadUserByUsername(principal.getName()));
        System.out.println(changePassword);
        if(this.passwordEncoder.matches(changePassword.getOldPassword(), user.getPassword())){
            user.setPassword(this.passwordEncoder.encode(changePassword.getNewPassword()));
            user = this.userService.updateUser(user);
            return user;
        }else{
            throw new PasswordNotMatch("Old password does not match. Please try again.");
        }
    }
//    Handling Error for this class.
    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException e) {
        return ResponseEntity.badRequest().body(e);
    }

    @ExceptionHandler(PasswordNotMatch.class)
    public ResponseEntity<?> exceptionHandler(PasswordNotMatch e) {
        return ResponseEntity.badRequest().body(e);
    }

}

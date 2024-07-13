package com.codeventure.helper;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super("User with this username is not found in DataBase..!");
    }
    public UserNotFoundException(String msg){ super(msg); }
}

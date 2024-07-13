package com.codeventure.helper;

public class UserFoundException extends Exception{
    public UserFoundException(){
        super("User with this username is already in Registered..! try another username...");
    }
    public UserFoundException(String msg){ super(msg); }
}

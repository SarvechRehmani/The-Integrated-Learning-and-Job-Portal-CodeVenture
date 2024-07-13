package com.codeventure.helper;

public class PasswordNotMatch extends Exception{
    public PasswordNotMatch(){
        super("Old password does not match. Please try again.");
    }
    public PasswordNotMatch(String msg){ super(msg); }
}

package com.codeventure.helper;

public class QuizNotFoundException extends Exception{
    public QuizNotFoundException(){
        super("Quiz Not Found.");
    }
    public QuizNotFoundException(String msg){ super(msg); }
}

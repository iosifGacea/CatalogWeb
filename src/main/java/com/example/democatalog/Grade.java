package com.example.democatalog;

import lombok.Data;

import java.util.Calendar;

public class Grade {
Subject subject;
int grade;
Calendar data;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }
    public Grade(Subject subject, int grade, Calendar data)
    {
        this.subject=subject;
        this.grade=grade;
        this.data=data;
    }
}

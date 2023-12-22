package com.example.democatalog;

public class Subject {
    int id;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Subject(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Subject(){}
}

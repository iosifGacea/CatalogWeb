package com.example.democatalog;

public class MyTouple {
    String first;
    String second;
    int third;

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public int getThird() {
        return third;
    }

    public void setThird(int third) {
        this.third = third;
    }
    public MyTouple(String f, String s, int t)
    {
        first=f;
        second=s;
        third=t;
    }
    public MyTouple(){}
}

package com.example.democatalog;

import ch.qos.logback.core.joran.sanity.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Db {
    public static List<Student> findAllStudents() throws SQLException {

            Connection _con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/catalog", "root", "123456");
            Statement statement = _con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM catalog.student;");
            List<Student> list = new ArrayList<>();
        while (resultSet.next())
        {
            int id=resultSet.getInt(1);
            String n=resultSet.getString(2);
            String p=resultSet.getString(3);
            Student s = new Student(id, n, p);
            list.add(s);
        }
            return list;
    }
    public  static List<Teacher> findAllTeachers() throws SQLException{
        Connection _con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/catalog", "root", "123456");
        Statement statement = _con.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM catalog.teacher;");
        List<Teacher> list = new ArrayList<>();
        while (resultSet.next())
        {
            int id=resultSet.getInt(1);
            String n=resultSet.getString(2);
            String p=resultSet.getString(3);
            Teacher t = new Teacher(id, n, p);
            list.add(t);
        }
        return list;
    }
    public  static List<Subject> findAllSubjects() throws SQLException {
        Connection _con = DriverManager.getConnection("jdbc:mysql://localhost:3306/catalog", "root", "123456");
        Statement statement = _con.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM catalog.subject;");
        List<Subject> list = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String n = resultSet.getString(2);
            Subject s = new Subject(id, n);
            list.add(s);
        }
        return list;
    }
    public static Calendar toCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
    public static List<Grade> findAllGradesForStudent(int idStudent) throws SQLException{
        Connection _con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/catalog", "root", "123456");
        Statement statement0 = _con.createStatement();
        Statement statement1 = _con.createStatement();
        String string = " select * from grade where id_student=" +  idStudent;
        ResultSet  gradesResulSet = statement0.executeQuery(string);
        ResultSet  subjectsResultSet = statement1.executeQuery(" select * from subject");
        List<Grade> grades = new ArrayList<>();
        //REFACTORIZEAZAAA
        List<MyPair> pairs = new ArrayList<>();
        while (subjectsResultSet.next())
        {
            MyPair pair = new MyPair(subjectsResultSet.getInt(1), subjectsResultSet.getString(2));
            pairs.add(pair);
        }

        while (gradesResulSet.next())
        {
            //  |id_student|id_subject|grade|data|
            int idSubject = gradesResulSet.getInt(2);
            String nameSubject = "";
            for(MyPair pair : pairs)
            {
                if(pair.first == idSubject)
                    nameSubject=pair.second;
            }
            Subject subject = new Subject(idSubject, nameSubject);
            grades.add(new Grade(subject, gradesResulSet.getInt(3), toCalendar(gradesResulSet.getDate(4))));
        }
        return grades;
    }
}
